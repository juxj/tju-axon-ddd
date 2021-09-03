package com.example.demo.handler;


import com.example.demo.command.TestQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
public class TestQueryHandler {

    @QueryHandler
    public String query(TestQuery query) {
        return "hello" + query.getId();
    }
}
