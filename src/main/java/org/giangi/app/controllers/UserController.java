package org.giangi.app.controllers;

import org.giangi.app.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.giangi.app.domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getUsers(){
        List<User> users = new ArrayList<>();
        userService.listAll().forEach(users::add);
       return users;
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id){
        try{
            return new ResponseEntity<User>(userService.get(id), HttpStatus.OK);
        }
        catch (NoSuchElementException e){
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/users/new")
    public void addUser(@RequestBody User user){
        userService.save(user);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable Long id){
        try {
            User toUp = userService.get(id);
            userService.save(user);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("users/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.delete(id);
    }
}
