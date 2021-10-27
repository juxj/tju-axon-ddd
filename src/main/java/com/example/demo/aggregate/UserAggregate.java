package com.example.demo.aggregate;

import com.example.demo.cqe.command.CreateUserCommand;
import com.example.demo.cqe.command.UpdateUserNameCommand;
import com.example.demo.cqe.event.UserNameUpdatedEvent;
import com.example.demo.cqe.event.UserCreateEvent;
import com.example.demo.validator.UserNameValidator;
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
    @UserNameValidator
    public UserAggregate(CreateUserCommand cmd) {
        UserCreateEvent e = UserCreateEvent.builder().id(cmd.getId()).name(cmd.getName()).build();
        AggregateLifecycle.apply(e);
    }


    public void handle(UpdateUserNameCommand cmd) {
        UserNameUpdatedEvent e = UserNameUpdatedEvent.builder().id(cmd.getId()).name(cmd.getName()).build();
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
    void on(UserCreateEvent event) {
        this.id = event.getId();
        this.name = event.getName();
    }

    @EventSourcingHandler
    void on(UserNameUpdatedEvent e) {
        this.name = e.getName();
    }

}
