package com.example.demo.command;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateUserNameEvent {

    private String id;
    private String name;
}
