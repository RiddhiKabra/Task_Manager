package com.example.taskmanager.model;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "task")
public class Task {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @NotBlank
    @Column(nullable = false)
    public String title;

    public String description;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status Status;

    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
    
    public enum Status
    {
        TODO,
        IN_PROGRESS,
        DONE
    }
}
