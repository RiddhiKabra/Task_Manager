package com.example.taskmanager.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.model.Task.Status;
import com.example.taskmanager.repository.TaskRepository;

public class TaskServiceTest {
 
    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreatedTask()
    {
        Task expectedTask = new Task();
        expectedTask.setTitle("Test Task");
        expectedTask.setStatus(Status.DONE);

        when(taskRepository.save(expectedTask)).thenReturn(expectedTask);

        Task actualTask = taskService.createTask(expectedTask);
        assertNotNull(actualTask);
        assertEquals(expectedTask.getTitle(), actualTask.getTitle());
        assertEquals(expectedTask.getStatus(), actualTask.getStatus());
        verify(taskRepository, times(1)).save(expectedTask);
    }
    
    @Test
    public void testUpdateTask() {
        Long taskId = 1L;

        Task existingTask = new Task();
        existingTask.setId(taskId);
        existingTask.setTitle("Old Title");
        existingTask.setStatus(Status.TODO);

        Task updatedTask = new Task();
        updatedTask.setTitle("New Title");
        updatedTask.setStatus(Status.DONE);


        when(taskRepository.findById(taskId)).thenReturn(Optional.of(existingTask));
        when(taskRepository.save(any(Task.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Task result = taskService.updateTask(taskId, updatedTask);

        assertEquals("New Title", result.getTitle());
        assertEquals(Status.DONE, result.getStatus());

        verify(taskRepository).findById(taskId);
        verify(taskRepository).save(existingTask);
    }

}
