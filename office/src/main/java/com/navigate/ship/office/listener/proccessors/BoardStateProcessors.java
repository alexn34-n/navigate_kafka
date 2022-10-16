package com.navigate.ship.office.listener.proccessors;

import com.navigate.ship.common.bean.Board;
import com.navigate.ship.common.bean.Port;
import com.navigate.ship.common.bean.Route;
import com.navigate.ship.common.messages.BoardStateMessage;
import com.navigate.ship.common.messages.PortStateMessage;
import com.navigate.ship.common.processor.MessageConverter;
import com.navigate.ship.common.processor.MessageProcessor;
import com.navigate.ship.office.provider.BoardsProvider;
import com.navigate.ship.office.provider.PortsProvider;
import com.navigate.ship.office.service.WaitingRouteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component("BOARD_STATE")
@RequiredArgsConstructor
public class BoardStateProcessors implements MessageProcessor<BoardStateMessage> {

    private  final MessageConverter messageConverter;
    private  final KafkaTemplate<String,String> kafkaTemplate;

    private  final WaitingRouteService waitingRouteService;
    private  final BoardsProvider boardsProvider;
    private  final PortsProvider portsProvider;


    @Override
    public void process(String jsonMessage) {
        BoardStateMessage message = messageConverter.extractMessage(jsonMessage, BoardStateMessage.class);
        Board board = message.getBoard();
       Optional< Board> previousOpt = boardsProvider.getBoard(board.getName());
       Port port=portsProvider.getPort(board.getLocation());

       boardsProvider.addBoard(board);
       if(previousOpt.isPresent() && board.hasRoute() && !previousOpt.get().hasRoute()){
             Route route = board.getRoute();
             waitingRouteService.remove(route);
       }
       if (previousOpt.isEmpty() || !board.isBusy() && previousOpt.get().isBusy()){
            port.addBoard(board.getName());
            kafkaTemplate.sendDefault(messageConverter.toJson(new PortStateMessage(port)));
       }

    }
}
