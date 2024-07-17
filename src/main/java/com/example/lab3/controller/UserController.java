package com.example.lab3.controller;

import com.example.lab3.DTOs.UserAllDataDTO;
import com.example.lab3.DTOs.UserNameDTO;
import com.example.lab3.exceptions.EmptyFieldException;
import com.example.lab3.model.User;
import com.example.lab3.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@RestController
public class UserController {

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    private final UserService userService;
    private final ModelMapper modelMapper;

    @PostMapping(value = "/create_user/")
    public ResponseEntity<String> createUser(@RequestBody UserAllDataDTO user) {
        User newUser;
        try { newUser = modelMapper.map(user, User.class); }
        catch (NullPointerException e) { throw new EmptyFieldException("Некоторые поля запроса не заполнены."); }
        userService.createUser(newUser);
        return ResponseEntity.ok("Пользователь успешно добавлен.");
    }

    @GetMapping(value = "/user/")
    public ResponseEntity<UserNameDTO> readUser(@RequestBody(required = false) Long id) {
        if (id == null) throw new EmptyFieldException("Поле ID не заполнено.");
        var user = userService.readUserById(id);
        var userDTO = modelMapper.map(user, UserNameDTO.class);
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping(value = "/users/")
    public ResponseEntity<ArrayList<UserNameDTO>> readAllUsers() {
        var users = userService.readAllUsers();
        var usersDTO = new ArrayList<UserNameDTO>();
        users.forEach((user) -> usersDTO.add(modelMapper.map(user, UserNameDTO.class)));
        return ResponseEntity.ok(usersDTO);
    }

    @PutMapping(value = "/update_user/")
    public ResponseEntity<String> updateUser(@RequestBody UserAllDataDTO new_user) {
        if (new_user.getId() == null)
            throw new EmptyFieldException("Поле ID не заполнено.");
        userService.updateUser(new_user);
        return ResponseEntity.ok("Пользователь успешно обновлен.");
    }

    @DeleteMapping(value = "/delete_user/")
    public ResponseEntity<String> deleteUser(@RequestBody(required = false) Long id) {
        if (id == null) throw new EmptyFieldException("Поле ID не заполнено.");
        userService.deleteUser(id);
        return ResponseEntity.ok("Пользователь успешно удален.");
    }

}
