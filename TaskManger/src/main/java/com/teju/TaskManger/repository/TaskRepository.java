package com.teju.TaskManger.repository;

import com.teju.TaskManger.entity.Task;
import com.teju.TaskManger.util.TaskPriority;
import com.teju.TaskManger.util.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUserId(Long userId);
    List<Task> findByUserIdAndStatus(Long userId, TaskStatus status);
    List<Task> findByUserIdAndPriority(Long userId, TaskPriority priority);
    Optional<Task> findByIdAndUserId(Long id, Long userId);
}

