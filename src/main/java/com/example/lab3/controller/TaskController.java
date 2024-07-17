package com.example.lab3.controller;

import com.example.lab3.DTOs.TaskDTO;
import com.example.lab3.exceptions.EmptyFieldException;
import com.example.lab3.model.Task;
import com.example.lab3.service.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@RestController
public class TaskController {

    @Autowired
    public TaskController(TaskService taskService, ModelMapper modelMapper) {
        this.taskService = taskService;
        this.modelMapper = modelMapper;
    }

    private final TaskService taskService;
    private final ModelMapper modelMapper;

    @PostMapping(value = "/create_task/")
    public ResponseEntity<String> createTask(@RequestBody TaskDTO task) {
        if (task.getTitle() == null)
            throw new EmptyFieldException("Некоторые поля запроса не заполнены.");
        Task newTask = modelMapper.map(task, Task.class);
        taskService.createTask(newTask);
        return ResponseEntity.ok("Задача успешно добавлена.");
    }

    @GetMapping(value = "/task/")
    public ResponseEntity<TaskDTO> readTask(@RequestBody(required = false) Long id) {
        if (id == null) throw new EmptyFieldException("Поле ID не заполнено.");
        var task = taskService.readTaskById(id);
        var taskDTO = modelMapper.map(task, TaskDTO.class);
        return ResponseEntity.ok(taskDTO);
    }

    @GetMapping(value = "/tasks/")
    public ResponseEntity<ArrayList<TaskDTO>> readAllTasks() {
        var tasks = taskService.readAllTasks();
        var tasksDTO = new ArrayList<TaskDTO>();
        tasks.forEach((task) -> tasksDTO.add(modelMapper.map(task, TaskDTO.class)));
        return ResponseEntity.ok(tasksDTO);
    }

    @PutMapping(value = "/update_task/")
    public ResponseEntity<String> updateTask(@RequestBody TaskDTO newTask) {
        if (newTask.getId() == null)
            throw new EmptyFieldException("Поле ID не заполнено.");
        taskService.updateTask(newTask);
        return ResponseEntity.ok("Задача успешно обновлена.");
    }

    @DeleteMapping(value = "/delete_task/")
    public ResponseEntity<String> deleteTask(@RequestBody(required = false) Long id) {
        if (id == null) throw new EmptyFieldException("Поле ID не заполнено.");
        taskService.deleteTask(id);
        return ResponseEntity.ok("Задача успешно удалена.");
    }

}
