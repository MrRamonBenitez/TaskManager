package ru.netology.javacore;

import java.io.*;

public class TodoServer_My implements AutoCloseable {
    int port;
    Todos todos;

    public TodoServer_My(int port, Todos todos) {
        this.port = port;
        this.todos = todos;
    }

    public void start() throws IOException {
        System.out.println("Starting server at " + port + "...");
//        while (true) {
//            try (ServerSocket serverSocket = new ServerSocket(port);
//                 Socket clientSocket = serverSocket.accept();
//                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
//                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//            ) {
//                out.println("Greetings, good friend! This is Task Manager! Looking forward to your commands!");
//                String msg = in.readLine();
//
//            }
//        }
    }

    @Override
    public void close() throws Exception {

    }
}
