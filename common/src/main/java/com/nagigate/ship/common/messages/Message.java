package com.nagigate.ship.common.messages;


import com.itprom.ship.common.bean.Source;
import com.itprom.ship.common.bean.Type;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Message {
    protected Type type;
    protected Source source;

    public  String getCode(){
         return  source.name() + "_" + type.name();
    }
}
