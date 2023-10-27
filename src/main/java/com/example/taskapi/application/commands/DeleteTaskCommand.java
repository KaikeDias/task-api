package com.example.taskapi.application.commands;

import com.example.taskapi.domain.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteTaskCommand {
    final TaskRepository taskRepository;

    @Autowired
    public DeleteTaskCommand(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void execute(Integer id) {
        taskRepository.deleteById(id);
    }
}
