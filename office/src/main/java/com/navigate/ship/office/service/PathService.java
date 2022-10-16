package com.navigate.ship.office.service;

import com.navigate.ship.common.bean.Route;
import com.navigate.ship.common.bean.RoutePath;
import com.navigate.ship.common.bean.RoutePoint;
import com.navigate.ship.office.provider.PortsProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PathService {
    private  final PortsProvider portsProvider;

    public RoutePath makePath(String from, String to){
        return  new RoutePath(portsProvider.getRoutePoint(from),portsProvider.getRoutePoint(to),0);

    }

    public Route convertLocationsToRoute(List<String> locations){
        Route route = new Route();
        List<RoutePath> path = new ArrayList<>();
        List<RoutePoint>  points = new ArrayList<>();

        locations.forEach(location->{
            portsProvider.getPorts().stream()
                    .filter(port -> port.getName().equals(location))
                    .findFirst()
                    .ifPresent(port -> points.add(new RoutePoint(port)));

        });

        for (int i = 0; i < points.size()-1; i++) {
            path.add(new RoutePath());
        }
        path.forEach(row->{
            int ind = path.indexOf(row);
            if(row.getFrom()==null){
                row.setFrom(points.get(ind));
                if (points.size()>ind+1){
                    row.setTo(points.get(ind+1));
                }else {
                    row.setTo(points.get(ind));
                }
            }
        });

        route.setPath(path);

        return route;

    }
}
