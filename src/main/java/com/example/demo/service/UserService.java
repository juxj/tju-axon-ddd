package com.example.demo.service;

import com.example.demo.cqe.command.CreateUserCommand;
import com.example.demo.cqe.command.UpdateUserNameCommand;
import com.example.demo.cqe.query.UserQueryById;
import com.example.demo.model.User;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.CompletableFuture;

@Service
public class UserService implements IUserService {


    @Resource
    private CommandGateway commandGateway;

    @Resource
    private QueryGateway queryGateway;

    @Override
    public void createUser(String id, String name) {
        CreateUserCommand cmd = CreateUserCommand.builder().id(id).name(name).build();
        commandGateway.sendAndWait(cmd);
    }

    @Override
    public User getUser(String id) throws Exception {
        UserQueryById q = UserQueryById.builder().id(id).build();
        CompletableFuture<User> cf = queryGateway.query(q, User.class);
        return cf.get();
    }

    @Override
    public void updateUserName(String id, String name) {
        UpdateUserNameCommand cmd = UpdateUserNameCommand.builder().id(id).name(name).build();
        commandGateway.sendAndWait(cmd);
    }
}
