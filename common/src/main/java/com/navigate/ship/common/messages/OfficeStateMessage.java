package com.navigate.ship.common.messages;

import com.navigate.ship.common.bean.Route;
import com.navigate.ship.common.bean.Source;
import com.navigate.ship.common.bean.Type;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OfficeStateMessage extends  Message{

    private Route route;

    public OfficeStateMessage() {
        this.source = Source.OFFICE;
        this.type= Type.STATE;
    }


}
