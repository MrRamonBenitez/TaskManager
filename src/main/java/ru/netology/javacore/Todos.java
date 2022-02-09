package ru.netology.javacore;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.System.out;

public class Todos {
    private final List<String> taskList = new ArrayList<>();

    public void addTask(String task) {
        if ( taskList.isEmpty () || !taskList.contains ( task ) ) {
            taskList.add ( task );
        } else out.printf ( "Task %s already exists in the list", task );
//        taskList.stream()
//                .sorted ()
//                .forEach (System.out::println);
    }

    public void removeTask(String task) {
        if (taskList.isEmpty ()) out.println ( "Task list is empty!");
        if ( taskList.contains ( task ) ) taskList.remove ( task );
        else out.printf ( "Task %s does not exist in the list!", task);
//        taskList.stream()
//                .sorted ()
//                .forEach (System.out::println);
    }

    public void getAllTasks() {
        String msg;
//        taskList.stream()
//            .sorted ()
//            .forEach (System.out::println);
    }

}
