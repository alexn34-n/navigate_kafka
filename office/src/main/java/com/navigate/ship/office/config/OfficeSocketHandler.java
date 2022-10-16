package com.navigate.ship.office.config;


import com.github.benmanes.caffeine.cache.Cache;
import com.navigate.ship.common.messages.OfficeStateMessage;
import com.navigate.ship.common.processor.MessageConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Slf4j
@Component
@RequiredArgsConstructor
public class OfficeSocketHandler extends TextWebSocketHandler {

    private final MessageConverter messageConverter;
    private final Cache<String, WebSocketSession> sessionCache;
    private  final KafkaTemplate<String,String> kafkaTemplate;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        if(session.isOpen()){
            sessionCache.put(session.getId(),session);
        }
        if(message.getPayload().equals("update")){
            kafkaTemplate.sendDefault(messageConverter.toJson(new OfficeStateMessage()));
        }

    }
}
