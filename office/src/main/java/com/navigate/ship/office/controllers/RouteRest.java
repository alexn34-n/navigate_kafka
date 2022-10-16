package com.navigate.ship.office.controllers;

import com.navigate.ship.common.bean.Route;
import com.navigate.ship.office.service.PathService;
import com.navigate.ship.office.service.WaitingRouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/routes")
public class RouteRest {

    private  final PathService pathService;
    private  final WaitingRouteService waitingRouteService;

    @PostMapping(path = "route")

    public void  addRoute(@RequestBody List<String> routeLocations){
        Route route = pathService.convertLocationsToRoute(routeLocations);
        waitingRouteService.add(route);

    }

}
