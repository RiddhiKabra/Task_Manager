package com.example.taskmanager.service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.taskmanager.exception.TaskNotFoundException;
import com.example.taskmanager.model.Task;
import com.example.taskmanager.repository.TaskRepository;
import com.example.taskmanager.search.TaskSearchService;



@Service
public class TaskService {

    private final TaskRepository repo;
    private final TaskSearchService searchService;

    public TaskService(TaskRepository repo, TaskSearchService searchService)
    {
        this.repo = repo;
        this.searchService = searchService;
    }

    //getAll
    public List<Task> getAll()
    {
        return repo.findAll();
    }

    // get by id
    public Task getTaskById(Long id)
    {
        return repo.findById(id).
                    orElseThrow(() -> new TaskNotFoundException(id));
    }

    //create 
    public Task createTask(Task task)
    {
        return repo.save(task);
    }

    //update
    public Task updateTask(Long Id, Task updatedTask)
    {
        Task existingTask = getTaskById(Id);
        
        if(updatedTask.getTitle() != null)
        {
            existingTask.setTitle(updatedTask.getTitle());
        }

        if(updatedTask.getDescription() != null)
        {
            existingTask.setDescription(updatedTask.getDescription());
        }

        if(updatedTask.getStatus() != null)
        {
            existingTask.setStatus(updatedTask.getStatus());
        }
        if (updatedTask.getDueDate() != null)
            existingTask.setDueDate(updatedTask.getDueDate());
        if (updatedTask.getPriority() != null)
            existingTask.setPriority(updatedTask.getPriority());

        return repo.save(existingTask);
    }

    //delete
    public void deleteTask(Long id)
    {
        if(!repo.existsById(id))
        {
            throw new TaskNotFoundException(id);
        }
        repo.deleteById(id);
    }

    public List<Task> getSortedTasks()
    {
        List<Task> tasks = repo.findAll();
        return tasks.stream()
        .sorted(Comparator.comparing(Task::getPriority, Comparator.nullsLast(Integer::compareTo))
        .thenComparing(Task::getDueDate, Comparator.nullsLast(LocalDateTime::compareTo)))
        .collect(Collectors.toList());
    }

    // public List<Task> searchTasksByTitle(String keyword) {
    //     return repo.findAll().stream()
    //         .filter(task -> task.getTitle() != null &&
    //                task.getTitle().toLowerCase().contains(keyword.toLowerCase()))
    //         .collect(Collectors.toList());
    // }

    public List<Task> searchTasksByPrefix(String prefix) {
        return searchService.search(prefix);
    }
}
