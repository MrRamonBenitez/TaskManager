package ru.netology.javacore;

import com.google.gson.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.stream.Collectors;

import static java.lang.System.*;

public class TodoServer implements AutoCloseable {
    int port;
    Todos todos;

    public TodoServer(int port, Todos todos) {
        this.port = port;
        this.todos = todos;
    }

    public void start() throws IOException {
        out.println ( "Starting server at " + port + "..." );
        while (true) {
            try (ServerSocket serverSocket = new ServerSocket ( port );
                 Socket clientSocket = serverSocket.accept ();
                 PrintWriter out = new PrintWriter ( clientSocket.getOutputStream (), true );
                 BufferedReader in = new BufferedReader ( new InputStreamReader ( clientSocket.getInputStream () ) );)
            {
                String json = in.readLine ();
                incomingJsonProcessing ( json );
            }
        }

    }

    private void incomingJsonProcessing(String json) {
        GsonBuilder builder = new GsonBuilder ();
        Gson gson = builder.create ();
        Todo todo = gson.fromJson (json, Todo.class );
        String typeCommand = todo.getType ();
        String task = todo.getTask ();
        if ( "ADD".equals( typeCommand ) ) {
            todos.addTask ( task );
            out.println (printActualTask ());
        }
        if ( "REMOVE".equals( typeCommand ) ) {
            todos.removeTask ( task );
            out.println ( printActualTask () );
        }
        if ( "GET ALL".equals( typeCommand ) ) {
            out.println (todos.getAllTasks ());
        }
    }

    private String printActualTask() {
        StringBuilder sb = new StringBuilder ();
        if ( todos.getTaskList ().isEmpty () ) {
            out.println ( "Task list is empty!" );
            return "";
        } else {
            Iterator<String> it = todos.getTaskList().iterator();
            while(it.hasNext()) {
                sb.append (it.next() + " ");
            }
            return sb.toString ();
        }
    }

    @Override
    public void close() throws Exception {
        out.println ("Server close");
    }
}
