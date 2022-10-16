package com.navigate.ship.office.listener.proccessors;

import com.navigate.ship.common.messages.OfficeStateMessage;
import com.navigate.ship.common.messages.PortStateMessage;
import com.navigate.ship.common.processor.MessageConverter;
import com.navigate.ship.common.processor.MessageProcessor;
import com.navigate.ship.office.provider.PortsProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component("OFFICE_STATE")
@RequiredArgsConstructor
public class OfficerStateProcessors  implements MessageProcessor<OfficeStateMessage> {

    private  final MessageConverter messageConverter;
    private  final PortsProvider portsProvider;
    private  final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void process(String jsonMessage) {
         portsProvider.getPorts().forEach(port -> {
             kafkaTemplate.sendDefault(messageConverter.toJson(new PortStateMessage(port)));
         });

    }
}
