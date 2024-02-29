package com.picpaychallenger.infra.controller;

import com.picpaychallenger.domain.users.service.UserService;
import com.picpaychallenger.domain.users.entity.User;
import com.picpaychallenger.domain.users.dto.CreateUserRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody CreateUserRequestDTO data) {
        System.out.println(data);
        User user = this.userService.createUser(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(this.userService.getAll());
    }

}
