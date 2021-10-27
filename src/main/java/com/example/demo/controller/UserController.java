package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.IUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService testService;

    @PostMapping("/{id}/{name}")
    public void createUser(@PathVariable String id, @PathVariable String name) {
        testService.createUser(id, name);
    }

    @PutMapping("/{id}/{name}")
    public void updateUserName(@PathVariable String id, @PathVariable String name) {
        testService.updateUserName(id, name);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable String id) throws Exception {
        return testService.getUser(id);
    }
}
