package com.example.taskmanager.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import com.example.taskmanager.model.Task;
import com.example.taskmanager.service.TaskService;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    
    private final TaskService service;

    public TaskController(TaskService service)
    {
        this.service = service;
    }

    //get 
    @GetMapping
    public ResponseEntity<List<Task>> getAllTask()
    {
        return ResponseEntity.ok(service.getAll());
    }

    //get by id
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id)
    {
        return ResponseEntity.ok(service.getTaskById(id));
    }

    @PostMapping()
    public ResponseEntity<Task> createTask(@Valid @RequestBody Task task)
    {
        return ResponseEntity.ok(service.createTask(task));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task)
    {
        return ResponseEntity.ok(service.updateTask(id, task));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Task> deleteById(@PathVariable Long id)
    {
        service.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/sorted")
    public ResponseEntity<List<Task>> getSortedTasks() {
        return ResponseEntity.ok(service.getSortedTasks());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Task>> searchTasks(@RequestParam String keyword) {
        return ResponseEntity.ok(service.searchTasksByPrefix(keyword));
    }   
}
