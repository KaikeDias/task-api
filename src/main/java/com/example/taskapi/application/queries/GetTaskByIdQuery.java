package com.example.taskapi.application.queries;

import com.example.taskapi.domain.entities.Task;
import com.example.taskapi.domain.repositories.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.util.UUID;

@Component
public class GetTaskByIdQuery {
    final TaskRepository taskRepository;

    @Autowired
    public GetTaskByIdQuery(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task execute(Integer id) {
        return taskRepository.findById(id).orElseThrow(
                EntityNotFoundException::new
        );
    }
}
