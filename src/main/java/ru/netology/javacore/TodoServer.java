package ru.netology.javacore;

import com.google.gson.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import static java.lang.System.*;

public class TodoServer implements AutoCloseable {
    private final int port;
    private final Todos todos;

    private final GsonBuilder builder = new GsonBuilder();
    private final StringBuilder sb = new StringBuilder();

    public TodoServer(int port, Todos todos) {
        this.port = port;
        this.todos = todos;
    }

    public void start() throws IOException {
        out.println("Starting server at " + port + "...");

        while (true) {
            try (ServerSocket serverSocket = new ServerSocket(port);
                 Socket clientSocket = serverSocket.accept();
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));) {

                incomingJsonProcessing(in.readLine(), out);
            }
        }
    }

    public void incomingJsonProcessing(String json, PrintWriter out) {
        Gson gson = builder.create();
        ClientCommand clientCommand = gson.fromJson(json, ClientCommand.class);

        String typeCommand = clientCommand.getType();
        String task = clientCommand.getTask();

        if ("ADD".equals(typeCommand)) {
            todos.addTask(task);
            out.println(printActualTask(out));
        }
        if ("REMOVE".equals(typeCommand)) {
            todos.removeTask(task);
            out.println(printActualTask(out));
        }
        if ("GET ALL".equals(typeCommand)) {
            out.println(todos.getAllTasks());
        }
    }

    private String printActualTask(PrintWriter out) {
        if (todos.getTaskList().isEmpty()) {
            out.println("Task list is empty!");
            return null;
        } else {
            for (String s : todos.getTaskList()) {
                sb.append(s).append(" ");
            }
            return sb.toString();
        }
    }

    @Override
    public void close() { out.println("Server close"); }
}
