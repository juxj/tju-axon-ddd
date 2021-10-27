package com.example.demo.command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class UpdateUserNameCommand {

    @TargetAggregateIdentifier
    private String id;

    private String name;
}