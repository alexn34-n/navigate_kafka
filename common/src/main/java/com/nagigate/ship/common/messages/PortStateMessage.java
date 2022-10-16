package com.nagigate.ship.common.messages;

import com.itprom.ship.common.bean.Port;
import com.itprom.ship.common.bean.Source;
import com.itprom.ship.common.bean.Type;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PortStateMessage extends  Message{

    private Port  port;

    public PortStateMessage() {
        this.source = Source.PORT;
        this.type= Type.STATE;
    }

    public PortStateMessage(Port port) {
        this();
        this.port = port;
    }
}
