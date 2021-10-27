package com.example.demo.handler;

import com.example.demo.command.UpdateUserNameEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@ProcessingGroup("amqpEvents")
public class RabbitEventHandler {

    @EventHandler
    void on(UpdateUserNameEvent e) {
        log.info("2");
    }
}
