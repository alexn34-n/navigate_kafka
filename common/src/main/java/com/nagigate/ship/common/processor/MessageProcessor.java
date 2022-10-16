package com.nagigate.ship.common.processor;

import com.itprom.ship.common.messages.Message;

public interface MessageProcessor<T extends Message> {

    void  process(String jsonMessage);
}
