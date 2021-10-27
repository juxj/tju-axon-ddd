package com.example.demo.cqe.event;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserNameUpdatedEvent {

    private String id;
    private String name;
}
