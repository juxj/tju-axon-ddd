package com.example.demo.handler;

import com.example.demo.aggregate.TestAggregate;
import com.example.demo.command.TestCommand2;
import com.example.demo.command.TestEvent;
import com.example.demo.validator.TestCommandValidator;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.messaging.InterceptorChain;
import org.axonframework.modelling.command.CommandHandlerInterceptor;
import org.axonframework.modelling.command.Repository;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TestCommandHandler {



    private final Repository<TestAggregate> testAggregateRepository;

    public TestCommandHandler(Repository<TestAggregate> testAggregateRepository) {
        this.testAggregateRepository = testAggregateRepository;
    }

    /**
     * 此处可以通过Around的方法进行验证
     * @param cmd cmd
     * @throws Exception .
     */
    @CommandHandler
    @TestCommandValidator
    void handler22(TestCommand2 cmd) throws Exception {
        log.info("hello 22. {}", cmd.getName());
        TestEvent e = TestEvent.builder().id(cmd.getId()).name(cmd.getName()).build();
        testAggregateRepository.load(cmd.getId()).execute(x -> x.handle(cmd));
    }



}
