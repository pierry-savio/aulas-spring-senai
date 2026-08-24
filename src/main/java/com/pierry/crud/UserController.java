package com.pierry.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    final UserRepository userRepository;

    @GetMapping
    public List<User> listAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable UUID id) {

        Optional<User> userOpt = userRepository.findAllById(id);

        if (userOpt.isPresent()){
            return userOpt.get();
        } else{
            throw new RuntimeException("Usuário não encontrado");
        }
    }

    @PostMapping
    public User addUser(@RequestBody User user){
        return userRepository.save(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable UUID id, @RequestBody User user){
        User currentUser = getUserById(id);
        currentUser.setName(user.getName());
        currentUser.setCpf(user.getCpf());
        currentUser.setEmail(user.getEmail());
        return userRepository.save(currentUser);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable UUID id){
        userRepository.deleteById(id);
    }
}
