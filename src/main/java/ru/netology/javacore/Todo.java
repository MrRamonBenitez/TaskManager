package ru.netology.javacore;

public class Todo {
    private final String type;
    private final String task;

    public Todo(String type, String task) {
        this.type = type;
        this.task = task;
    }

    public String getType() {
        return type;
    }

    public String getTask() {
        return task;
    }

    @Override
    public String toString() {
        return "Todo: " +
                " type = " + type +
                " task = " + task
                ;
    }
}
