package com.example.demo.handler;


import com.example.demo.aggregate.UserAggregate;
import com.example.demo.cqe.query.UserQueryById;
import com.example.demo.model.User;
import org.axonframework.modelling.command.Aggregate;
import org.axonframework.modelling.command.Repository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
public class UserQueryHandler {


    private final Repository<UserAggregate> testAggregateRepository;

    public UserQueryHandler(Repository<UserAggregate> testAggregateRepository) {
        this.testAggregateRepository = testAggregateRepository;
    }

    @QueryHandler
    public User query(UserQueryById query) {
        Aggregate<UserAggregate> aggregate = testAggregateRepository.load(query.getId());
        if (null == aggregate) return null;
        return User.builder().id(aggregate.identifierAsString()).name("").build();
    }

}
