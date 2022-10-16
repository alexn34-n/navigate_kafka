package com.navigate.ship.common.messages;


import com.navigate.ship.common.bean.Route;
import com.navigate.ship.common.bean.Source;
import com.navigate.ship.common.bean.Type;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OfficeRouteMessage extends  Message{

    private Route route;

    public OfficeRouteMessage() {
        this.source = Source.OFFICE;
        this.type= Type.ROUTE;
    }

    public OfficeRouteMessage(Route val) {
        this();
        this.route = val;
    }
}
