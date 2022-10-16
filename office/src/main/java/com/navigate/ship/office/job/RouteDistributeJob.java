package com.navigate.ship.office.job;


import com.navigate.ship.common.bean.Board;
import com.navigate.ship.common.bean.Port;
import com.navigate.ship.common.bean.Route;
import com.navigate.ship.common.bean.RoutePath;
import com.navigate.ship.common.messages.OfficeRouteMessage;
import com.navigate.ship.common.messages.PortStateMessage;
import com.navigate.ship.common.processor.MessageConverter;
import com.navigate.ship.office.provider.BoardsProvider;
import com.navigate.ship.office.provider.PortsProvider;
import com.navigate.ship.office.service.PathService;
import com.navigate.ship.office.service.WaitingRouteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@EnableScheduling
@RequiredArgsConstructor
public class RouteDistributeJob {

    private  final PathService pathService;
    private  final BoardsProvider boardsProvider;
    private  final WaitingRouteService waitingRouteService;
    private  final PortsProvider portsProvider;

    private  final KafkaTemplate<String, String> kafkaTemplate;
    private final MessageConverter messageConverter;

    @Scheduled(initialDelay = 500, fixedDelay = 2500)
    private  void  distribute(){
        waitingRouteService.list().stream()
                .filter(Route::notAssigned)
                .forEach(route -> {
                    String startLocation = route.getPath().get(0).getFrom().getName();

                    boardsProvider.getBoards().stream()
                            .filter(board -> startLocation.equals(board.getLocation()) && board.noBusy())
                            .findFirst()
                            .ifPresent(board -> sendBoardToRoute(route, board));

                    if(route.notAssigned()){
                        boardsProvider.getBoards().stream()
                                .filter(Board::noBusy)
                                .findFirst()
                                .ifPresent(board->{
                                    String currentLocation = board.getLocation();
                                    if(!currentLocation.equals(startLocation)){
                                        RoutePath routePath = pathService.makePath(currentLocation, startLocation);
                                        route.getPath().add(0,routePath);
                                    }
                                    sendBoardToRoute(route,board);
                                });
                    }
                });

    }
    private void sendBoardToRoute(Route route, Board board){
        route.setBoardName(board.getName());
        Port port =portsProvider.findPortAndRemoveBoard(board.getName());
        board.setLocation(null);
        kafkaTemplate.sendDefault(messageConverter.toJson(new OfficeRouteMessage(route)));
        kafkaTemplate.sendDefault(messageConverter.toJson(new PortStateMessage(port)));
    }

}
