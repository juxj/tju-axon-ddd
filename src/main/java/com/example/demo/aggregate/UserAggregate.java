package com.example.demo.aggregate;

import com.example.demo.command.CreateUserCommand;
import com.example.demo.command.UpdateUserNameCommand;
import com.example.demo.command.UpdateUserNameEvent;
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

@Slf4j
@Data
@NoArgsConstructor
@Aggregate(repository = "testAggregateEventSourcingRepository")
public class UserAggregate {

    @AggregateIdentifier
    private String id;

    private String name;

    @CommandHandler
    @TestCommandValidator
    public UserAggregate(CreateUserCommand cmd) {
        log.info("hello 11. {}", cmd.getName());
        UpdateUserNameEvent e = UpdateUserNameEvent.builder().id(cmd.getId()).name(cmd.getName()).build();
        AggregateLifecycle.apply(e);
    }



    @CommandHandler
    void handler(CreateUserCommand cmd) {
        log.info("hello 12. {}", cmd.getName());
        UpdateUserNameEvent e = UpdateUserNameEvent.builder().id(cmd.getId()).name(cmd.getName()).build();
        AggregateLifecycle.apply(e);
    }

    public void handle(UpdateUserNameCommand cmd) {
        UpdateUserNameEvent e = UpdateUserNameEvent.builder().id(cmd.getId()).name(cmd.getName()).build();
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
    void intercept(UpdateUserNameCommand command, InterceptorChain interceptorChain) throws Exception {
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
    void intercept(CreateUserCommand command, InterceptorChain interceptorChain) throws Exception {
        log.info("...");
        interceptorChain.proceed();
    }




    @EventSourcingHandler
    void on(UpdateUserNameEvent e) {
        log.debug("event");
        this.id = e.getId();
        this.name = e.getName();
    }

}
