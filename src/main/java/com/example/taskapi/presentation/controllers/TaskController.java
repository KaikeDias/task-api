package com.example.taskapi.presentation.controllers;

import com.example.taskapi.application.Dtos.CreateTaskDto;
import com.example.taskapi.application.commands.CreateTaskCommand;
import com.example.taskapi.application.commands.DeleteTaskCommand;
import com.example.taskapi.application.commands.UpdateTaskStatusCommand;
import com.example.taskapi.application.queries.GetAllSortedQuery;
import com.example.taskapi.application.queries.GetTaskByIdQuery;
import com.example.taskapi.domain.entities.Task;
import com.example.taskapi.domain.repositories.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import org.apache.coyote.Response;
import org.hibernate.mapping.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RequestMapping("/tasks")
@RestController
public class TaskController {
    final CreateTaskCommand createTask;
    final GetTaskByIdQuery getTaskById;
    final UpdateTaskStatusCommand updateTaskStatus;
    final GetAllSortedQuery getAllSorted;
    final DeleteTaskCommand deleteTask;

    @Autowired
    public TaskController(CreateTaskCommand createTask, GetTaskByIdQuery getTaskById,
                          UpdateTaskStatusCommand updateTaskStatus, GetAllSortedQuery getAllSorted, DeleteTaskCommand deleteTask) {
        this.createTask = createTask;
        this.getTaskById = getTaskById;
        this.updateTaskStatus = updateTaskStatus;
        this.getAllSorted = getAllSorted;
        this.deleteTask = deleteTask;
    }

    @PostMapping("")
    public ResponseEntity<Task> createTask(@RequestBody CreateTaskDto dto) {
        Task task = createTask.execute(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(task);
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> getByID(@PathVariable Integer id) {
        try {
            Task task = getTaskById.execute(id);
            return ResponseEntity.ok(task);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping("{id}")
    public ResponseEntity<Object> updateStatus(@PathVariable Integer id) {
        try {
            Task task = updateTaskStatus.execute(id);

            return ResponseEntity.ok(task);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Task>> getTasksSorted(@RequestParam(required = false) String done,
            @RequestParam(required = false) String order_by) {
        var tasks = getAllSorted.execute(done, order_by);

        return ResponseEntity.ok(tasks);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Any> delete(@PathVariable Integer id) {
        deleteTask.execute(id);

        return ResponseEntity.noContent().build();
    }
}
