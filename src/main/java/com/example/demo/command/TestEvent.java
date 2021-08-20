package com.example.demo.command;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TestEvent {

    private String id;
    private String name;
}
