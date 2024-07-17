package com.example.lab3.service;

import com.example.lab3.DTOs.UserAllDataDTO;
import com.example.lab3.model.User;
import com.example.lab3.repository.UserRepository;
import com.example.lab3.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public void createUser(User user) {
        repository.save(user);
    }

    public User readUserById(Long id) {
        if (repository.count() == 0)
            throw new NotFoundException("База данных пустая.");
        return repository.findById(id).orElseThrow(() ->
                new NotFoundException("Пользователь с данным ID не найден."));
    }

    public Iterable<User> readAllUsers() {
        if (repository.count() == 0)
            throw new NotFoundException("База данных пустая.");
        return repository.findAll();
    }

    public void updateUser(UserAllDataDTO new_user) {
        User user = readUserById(new_user.getId());
        user.setName(new_user.getName());
        user.setLogin(new_user.getLogin());
        user.setPassword(new_user.getPassword());
        repository.save(user);
    }

    public void deleteUser(Long id) {
        User user = readUserById(id);
        repository.delete(user);
    }
}
