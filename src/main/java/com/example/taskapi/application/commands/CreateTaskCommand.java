package com.example.taskapi.application.commands;

import com.example.taskapi.application.Dtos.CreateTaskDto;
import com.example.taskapi.domain.entities.Task;
import com.example.taskapi.domain.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CreateTaskCommand {
    final TaskRepository taskRepository;

    @Autowired
    public CreateTaskCommand(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task execute(CreateTaskDto taskDto) {
        Task task = new Task();
        task.setContent(taskDto.getContent());
        task.setCreatedAt(LocalDateTime.now());
        task.setIsDone(false);

        return taskRepository.save(task);
    }
}
