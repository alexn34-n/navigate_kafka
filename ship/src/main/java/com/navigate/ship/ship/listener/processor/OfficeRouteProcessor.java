package com.navigate.ship.ship.listener.processor;


import com.navigate.ship.common.bean.Route;
import com.navigate.ship.common.messages.OfficeRouteMessage;
import com.navigate.ship.common.processor.MessageConverter;
import com.navigate.ship.common.processor.MessageProcessor;
import com.navigate.ship.ship.provider.BoardProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component("OFFICE_ROUTE")
@RequiredArgsConstructor
public class OfficeRouteProcessor implements MessageProcessor<OfficeRouteMessage> {

    private  final BoardProvider boardProvider;
    private final MessageConverter messageConverter;


    @Override
    public void process(String jsonMessage) {

        OfficeRouteMessage msg = messageConverter.extractMessage(jsonMessage,OfficeRouteMessage.class);
        Route route = msg.getRoute();
        boardProvider.getBoards().stream()
                .filter(board -> board.noBusy() &&  route.getBoardName().equals(board.getName()))
                .findFirst()
                .ifPresent(board -> {
                    board.setRoute(route);
                    board.setBusy(true);
                    board.setLocation(null);
                });
    }
}
