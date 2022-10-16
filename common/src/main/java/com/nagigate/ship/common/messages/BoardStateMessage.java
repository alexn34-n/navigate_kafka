package com.nagigate.ship.common.messages;

import com.itprom.ship.common.bean.Board;
import com.itprom.ship.common.bean.Source;
import com.itprom.ship.common.bean.Type;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardStateMessage extends  Message{

    private Board board;

    public BoardStateMessage() {
        this.source = Source.BOARD;
        this.type= Type.STATE;
    }

    public BoardStateMessage(Board val) {
        this();
        this.board = val;
    }
}
