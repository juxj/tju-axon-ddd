package com.example.demo.service;

import com.example.demo.command.TestCommand1;
import com.example.demo.command.TestQuery;
import org.aspectj.weaver.ast.Test;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class TestService implements ITestService {


    @Resource
    private CommandGateway commandGateway;

    @Resource
    private QueryGateway queryGateway;

    @Override
    public String sayHello(String name) {
        TestCommand1 cmd1 = TestCommand1.builder().id(UUID.randomUUID().toString()).name(name).build();
        return commandGateway.sendAndWait(cmd1);
    }

    @Override
    public String getName() throws Exception {
        TestQuery q = TestQuery.builder().id("....").build();
        CompletableFuture<String> cf = queryGateway.query(q, String.class);
        return cf.get();

    }
}
