// src/test/java/com/example/taskmanager/TaskIntegrationTest.java
package com.example.taskmanager.integrationTests;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.model.Task.Status;
import com.example.taskmanager.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class IntTest1 {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TaskRepository taskRepository;

    @Test
    void testGetAllTasksIntegration() throws Exception {
        // Arrange
        Task task1 = new Task();
        task1.setTitle("Test Task 1");
        task1.setStatus(Status.TODO);
        task1.setDescription("Desc 1");

        Task task2 = new Task();
        task2.setTitle("Test Task 2");
        task2.setStatus(Status.IN_PROGRESS);
        task2.setDescription("Desc 2");

        taskRepository.deleteAll(); // Clean existing data
        taskRepository.save(task1);
        taskRepository.save(task2);

        // Act & Assert
        mockMvc.perform(get("/api/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }
}
