package com.navigate.ship.common.messages;


import com.navigate.ship.common.bean.Source;
import com.navigate.ship.common.bean.Type;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Message {
    protected Type type;
    protected Source source;

    public  String getCode(){
         return  source.name() + "_" + type.name();
    }
}
