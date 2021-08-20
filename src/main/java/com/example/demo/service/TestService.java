package com.example.demo.service;

import com.example.demo.command.TestCommand1;
import com.example.demo.command.TestCommand2;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

@Service
public class TestService implements ITestService {


    @Resource
    private CommandGateway commandGateway;

    @Override
    public String sayHello(String name) {
        TestCommand1 cmd1 = TestCommand1.builder().id(UUID.randomUUID().toString()).name(name).build();
        String id = commandGateway.sendAndWait(cmd1);

        TestCommand2 cmd2 = TestCommand2.builder().id(id).name("kkkk").build();
        return commandGateway.sendAndWait(cmd2);


        // TestCommand2 cmd2 = TestCommand2.builder().id(UUID.randomUUID().toString()).name(name).build();
        // return  commandGateway.sendAndWait(cmd2);
    }
}
