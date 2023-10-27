package com.example.taskapi.application.queries;

import com.example.taskapi.domain.entities.Task;
import com.example.taskapi.domain.entities.TaskCreationComparator;
import com.example.taskapi.domain.entities.TaskNameComparator;
import com.example.taskapi.domain.repositories.TaskRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GetAllSortedQuery {
    final TaskRepository taskRepository;

    public GetAllSortedQuery(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> execute(String isDone, String order_by) {
        var tasks = taskRepository.findAll();

        if(isDone != null) {
            if(isDone.equals("y")) {
                tasks = tasks.stream().filter(task -> task.getIsDone() == true).collect(Collectors.toList());
            } else if(isDone.equals("n")) {
                tasks = tasks.stream().filter(task -> task.getIsDone() == false).collect(Collectors.toList());
            }
        }

        if(order_by != null) {
            if(order_by.equals("created_at")) {
                tasks.sort(new TaskCreationComparator());
            } else if(order_by.equals(("name"))) {
                tasks.sort(new TaskNameComparator());
            }
        }

        return tasks;
    }
}
