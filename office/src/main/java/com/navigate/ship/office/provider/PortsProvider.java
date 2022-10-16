package com.navigate.ship.office.provider;

import com.navigate.ship.common.bean.Port;
import com.navigate.ship.common.bean.RoutePoint;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@Getter
@Component
@ConfigurationProperties(prefix = "application")
public class PortsProvider {
    private  final List<Port> ports = new ArrayList<>();

    public Port findPortAndRemoveBoard(String boardName){
        AtomicReference<Port> res = new AtomicReference<>();
        ports.stream().filter(port -> port.getBoards().contains(boardName))
                .findFirst()
                .ifPresent(port -> {
                    port.removeBoard(boardName);
                    res.set(port);
                });
        return res.get();
    }
    public  Port getPort(String portName){
        return ports.stream()
                .filter(port -> port.getName().equals(portName))
                .findFirst()
                .orElse(new Port());
            }

    public RoutePoint getRoutePoint(String portName){
        return  new RoutePoint(getPort(portName));

    }
}
