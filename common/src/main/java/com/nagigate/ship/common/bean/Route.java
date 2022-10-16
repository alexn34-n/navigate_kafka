package com.nagigate.ship.common.bean;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode

public class Route {
    private String boardName;
    private List<RoutePath> path = new ArrayList<>();

    public  boolean notAssigned(){
        return  boardName == null;
    }


}
