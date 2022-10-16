package com.nagigate.ship.common.messages;

import com.itprom.ship.common.bean.Route;
import com.itprom.ship.common.bean.Source;
import com.itprom.ship.common.bean.Type;
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
