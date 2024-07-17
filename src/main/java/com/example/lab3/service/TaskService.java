package com.example.lab3.service;

import com.example.lab3.DTOs.TaskDTO;
import com.example.lab3.model.Task;
import com.example.lab3.repository.TaskRepository;
import com.example.lab3.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private final TaskRepository repository;

    @Autowired
    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public void createTask(Task task) {
        repository.save(task);
    }

    public Task readTaskById(Long id) {
        if (repository.count() == 0)
            throw new NotFoundException("База данных пустая.");
        return repository.findById(id).orElseThrow(() ->
                new NotFoundException("Задача с данным ID не найдена."));
    }

    public Iterable<Task> readAllTasks() {
        if (repository.count() == 0)
            throw new NotFoundException("База данных пустая.");
        return repository.findAll();
    }

    public void updateTask(TaskDTO new_task) {
        Task task = readTaskById(new_task.getId());
        task.setTitle(new_task.getTitle());
        task.setContent(new_task.getContent());
        repository.save(task);
    }

    public void deleteTask(Long id) {
        Task task = readTaskById(id);
        repository.delete(task);
    }

}
