package com.example.taskapi.application.commands;

import com.example.taskapi.application.queries.GetTaskByIdQuery;
import com.example.taskapi.domain.entities.Task;
import com.example.taskapi.domain.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class UpdateTaskStatusCommand {
    final TaskRepository taskRepository;
    final GetTaskByIdQuery getTaskById;

    @Autowired
    public UpdateTaskStatusCommand(TaskRepository taskRepository, GetTaskByIdQuery getTaskById) {
        this.taskRepository = taskRepository;
        this.getTaskById = getTaskById;
    }

    public Task execute(Integer id) {
        Task task = getTaskById.execute(id);

        task.updateStatus();

        return taskRepository.save(task);
    }
}
