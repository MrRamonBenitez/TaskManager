package ru.netology.javacore;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.System.out;

public class Todos {
    private List<String> taskList;

    public Todos() {
        this.taskList = new ArrayList<>();
    }

    void setTaskList(List<String> taskList) {
        this.taskList = taskList;
    }

    public List<String> getTaskList() {
        return taskList;
    }

    public void addTask(String task) {
        if (taskList.contains(task)) {
            out.printf("Task %s already exists in the list", task);
        } else {
            taskList.add(task);
        }
    }

    public void removeTask(String task) {
        if (taskList.isEmpty()) {
            out.println("Task list is empty!");
        }
        if (taskList.contains(task)) {
            taskList.remove(task);
        } else {
            out.printf("Task %s does not exist in the list!", task);
        }
    }

    public String getAllTasks() {
        if (taskList.isEmpty()) {
            out.println("Task list is empty!");
            return null;
        } else {
            return taskList.stream()
                    .sorted()
                    .collect(Collectors.joining(" "));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Todos)) {
            return false;
        }
        Todos todos = (Todos) o;
        return taskList.equals(todos.taskList);
    }

    @Override
    public String toString() {
        return "Todos: "
                + " " + taskList.toString();
    }
}
