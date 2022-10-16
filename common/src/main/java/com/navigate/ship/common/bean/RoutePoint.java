package com.navigate.ship.common.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoutePoint {
    private  String name;
    private  double x;
    private  double y;

    public RoutePoint(Port port) {
        this.name= port.getName();
        this.x= port.getX();
        this.y= port.getY();
    }
}
