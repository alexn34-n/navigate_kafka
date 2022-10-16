package com.navigate.ship.ship.listener.processor;

import com.navigate.ship.common.messages.BoardStateMessage;
import com.navigate.ship.common.messages.OfficeStateMessage;
import com.navigate.ship.common.processor.MessageConverter;
import com.navigate.ship.common.processor.MessageProcessor;
import com.navigate.ship.ship.provider.BoardProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


@Slf4j
@Component("OFFICE_STATE")
@RequiredArgsConstructor

public class OfficeStateProcessor implements MessageProcessor<OfficeStateMessage> {

    private final MessageConverter messageConverter;
    private final BoardProvider boardProvider;
    private  final KafkaTemplate<String,String> kafkaTemplate;

    @Override
    public void process(String jsonMessage) {
          boardProvider.getBoards().forEach(board -> {
              kafkaTemplate.sendDefault(messageConverter.toJson(new BoardStateMessage(board)));
          });
    }
}
