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
        out.println("Starting server at " + port + "...");

        try (ServerSocket serverSocket = new ServerSocket(port);
             Socket clientSocket = serverSocket.accept();
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            while (true) {
                incomingJsonProcessing(in, out);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void incomingJsonProcessing(BufferedReader in, PrintWriter out) throws IOException {
        String json = in.readLine();

        if (null == json) {
            out.println("Command not found");
        } else {
            Gson gson = builder.create();
            ClientCommand clientCommand = gson.fromJson(json, ClientCommand.class);

            String typeCommand = clientCommand.getType();
            String task = clientCommand.getTask();

            switch (typeCommand) {
                case "ADD": {
                    todos.addTask(task);
                    out.println(printActualTask(out));
                    break;
                }
                case "REMOVE": {
                    todos.removeTask(task);
                    out.println(printActualTask(out));
                    break;
                }
                case "GET ALL": {
                    out.println(todos.getAllTasks());
                    break;
                }
            }
        }
    }

    private String printActualTask(PrintWriter out) {
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
    public void close() {
        out.println("Server close");
    }
}

