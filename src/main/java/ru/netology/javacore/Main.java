package ru.netology.javacore;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Todos todos = new Todos();
        TodoServer server = new TodoServer(8989, todos);
        server.start();
    }
}
