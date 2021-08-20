package com.example.demo.aggregate;

import com.example.demo.command.TestCommand1;
import com.example.demo.command.TestCommand2;
import com.example.demo.command.TestEvent;
import com.example.demo.service.ITestService2;
import com.example.demo.validator.TestCommandValidator;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.messaging.InterceptorChain;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.CommandHandlerInterceptor;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@NoArgsConstructor
@Data
@Aggregate(repository = "testAggregateEventSourcingRepository")
public class TestAggregate {

    @AggregateIdentifier
    private String id;

    private String name;

    @CommandHandler
    @TestCommandValidator
    public TestAggregate(TestCommand1 cmd) {
        log.info("hello 11. {}", cmd.getName());
        TestEvent e = TestEvent.builder().id(cmd.getId()).name(cmd.getName()).build();
        AggregateLifecycle.apply(e);
    }



    @CommandHandler
    void handler(TestCommand1 cmd) {
        log.info("hello 12. {}", cmd.getName());
        TestEvent e = TestEvent.builder().id(cmd.getId()).name(cmd.getName()).build();
        AggregateLifecycle.apply(e);
    }

    public void handle(TestCommand2 cmd) {
        TestEvent e = TestEvent.builder().id(cmd.getId()).name(cmd.getName()).build();
        AggregateLifecycle.apply(e);
    }

    /**
     * 对聚合内方法生效，不能注入Spring容器内的对象
     * 仅限在聚合被更改时的逻辑检查，如两次输入的密码是否一样，
     *
     * @param command          command
     * @param interceptorChain chain
     * @throws Exception .
     */
    @CommandHandlerInterceptor
    void intercept(TestCommand2 command, InterceptorChain interceptorChain) throws Exception {
        log.info("...");
        interceptorChain.proceed();
    }


    // @CommandHandler
    // void handler22(TestCommand2 cmd) throws Exception {
    //     log.info("hello 22. {}", cmd.getName());
    //     TestEvent e = TestEvent.builder().id(cmd.getId()).name(cmd.getName()).build();
    //     AggregateLifecycle.apply(e);
    // }
    //
    //
    // @CommandHandler
    // void handler23(TestCommand2 cmd) {
    //     log.info("hello 21. {}", cmd.getName());
    //     TestEvent e = TestEvent.builder().id(cmd.getId()).name(cmd.getName()).build();
    //     AggregateLifecycle.apply(e);
    // }





    /**
     * 对于TestCommand1拦截不生效
     *
     * @param command          command
     * @param interceptorChain chain
     * @throws Exception .
     */
    @CommandHandlerInterceptor
    void intercept(TestCommand1 command, InterceptorChain interceptorChain) throws Exception {
        log.info("...");
        interceptorChain.proceed();
    }




    @EventSourcingHandler
    void on(TestEvent e) {
        log.debug("event");
        this.id = e.getId();
        this.name = e.getName();
    }

}
