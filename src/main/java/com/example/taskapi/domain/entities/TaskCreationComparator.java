package com.example.taskapi.domain.entities;

import java.util.Comparator;

public class TaskCreationComparator implements Comparator<Task> {
    @Override
    public int compare(Task o1, Task o2) {
        if(o1.getCreatedAt().isBefore(o2.getCreatedAt())) {
            return -1;
        } else {
            return 1;
        }
    }
}
