package com.pierry.crud;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/user")
public class UserController {

    public ArrayList<User> users = new ArrayList<>();

    @GetMapping
    public ArrayList<User> listAllUsers() {
        return users;
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) {
        return users.get(id);
    }

    @PostMapping
    public User addUser(@RequestBody User user){
        users.add(user);
        return users.getLast();
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User user){
        User updatedUser = users.get(id);
        updatedUser.setName(user.getName());
        updatedUser.setCpf(user.getCpf());
        updatedUser.setEmail(user.getEmail());
        return updatedUser;
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id){
        users.remove(id);
    }
}
