package ru.netology.javacore;

import com.google.gson.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.stream.Collectors;

import static java.lang.System.*;

public class TodoServer implements AutoCloseable {
    private final int port;
    private final Todos todos;

    private final GsonBuilder builder = new GsonBuilder();

    public TodoServer(int port, Todos todos) {
        this.port = port;
        this.todos = todos;
    }

    public void start() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            out.println("Starting server at " + port + "...");

            while (true) {
                try (
                        Socket clientSocket = serverSocket.accept();
                        PrintWriter out =
                            new PrintWriter(clientSocket.getOutputStream(), true);
                        BufferedReader in =
                            new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
                ) {
                    String json;
                    while ((json = in.readLine()) != null) {
                        Gson gson = builder.create();
                        ClientCommand clientCommand = gson.fromJson(json, ClientCommand.class);

                        String typeCommand = clientCommand.getType();
                        String task = clientCommand.getTask();

                        if ("ADD".equals(typeCommand)) {
                            todos.addTask(task);
                            out.println(printActualTask(out));
                        } else if ("REMOVE".equals(typeCommand)) {
                            todos.removeTask(task);
                            out.println(printActualTask(out));
                        } else if ("GET ALL".equals(typeCommand)) {
                            out.println(todos.getAllTasks());
                        }
                   }
                }
            }

        } catch (IOException ex) {
                ex.printStackTrace(System.out);
        }
    }

    public String printActualTask (PrintWriter out){
        if (todos.getTaskList().isEmpty()) {
            out.println("Task list is empty!");
            return null;
        } else {
            return todos.getTaskList().stream()
                        .sorted()
                        .collect(Collectors.joining(" "));
        }
    }

    @Override
    public void close () {
        out.println("Server close");
    }

}

