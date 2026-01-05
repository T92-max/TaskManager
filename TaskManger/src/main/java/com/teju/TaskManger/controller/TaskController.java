package com.teju.TaskManger.controller;

import com.teju.TaskManger.dto.TaskRequest;
import com.teju.TaskManger.dto.TaskResponse;
import com.teju.TaskManger.entity.User;
import com.teju.TaskManger.service.TaskService;
import com.teju.TaskManger.util.TaskPriority;
import com.teju.TaskManger.util.TaskStatus;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")

public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping()
    public ResponseEntity<TaskResponse> createTask(
            @Valid @RequestBody TaskRequest request,
            @AuthenticationPrincipal User user
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.createTask(request, user));
    }

    @GetMapping
    public ResponseEntity<List<TaskResponse>> getAllTasks(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(taskService.getAllTasks(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTaskById(
            @PathVariable Long id,
            @AuthenticationPrincipal User user
    ) {
        return ResponseEntity.ok(taskService.getTaskById(id, user));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<TaskResponse>> getTasksByStatus(
            @PathVariable TaskStatus status,
            @AuthenticationPrincipal User user
    ) {
        return ResponseEntity.ok(taskService.getTasksByStatus(status, user));
    }

    @GetMapping("/priority/{priority}")
    public ResponseEntity<List<TaskResponse>> getTasksByPriority(
            @PathVariable TaskPriority priority,
            @AuthenticationPrincipal User user
    ) {
        return ResponseEntity.ok(taskService.getTasksByPriority(priority, user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> updateTask(
            @PathVariable Long id,
            @Valid @RequestBody TaskRequest request,
            @AuthenticationPrincipal User user
    ) {
        return ResponseEntity.ok(taskService.updateTask(id, request, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(
            @PathVariable Long id,
            @AuthenticationPrincipal User user
    ) {
        taskService.deleteTask(id, user);
        return ResponseEntity.noContent().build();
    }
}

