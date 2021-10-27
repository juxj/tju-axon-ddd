package com.example.demo.handler;

import com.example.demo.aggregate.UserAggregate;
import com.example.demo.command.UpdateUserNameCommand;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.modelling.command.Aggregate;
import org.axonframework.modelling.command.Repository;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserCommandHandler {


    private final Repository<UserAggregate> testAggregateRepository;

    public UserCommandHandler(Repository<UserAggregate> testAggregateRepository) {
        this.testAggregateRepository = testAggregateRepository;
    }

    /**
     * 此处可以通过Around的方法进行验证, 由Command已成为Spring容器的组件，不再需要通过切面的方式进行验证
     *
     * @param cmd cmd
     * @throws Exception .
     */
    @CommandHandler
    void handle(UpdateUserNameCommand cmd) throws Exception {
        Aggregate<UserAggregate> aggregate = testAggregateRepository.load(cmd.getId());
        if (null == aggregate) return;
        aggregate.execute(x -> x.handle(cmd));
    }


}
