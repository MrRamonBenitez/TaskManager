package ru.netology.javacore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

public class Client {
    public static char pickRandomChar() {
        String chars = "ABCDEFGHJKLMNOPQRSTUWVXYZ";
        return chars.charAt(new Random().nextInt(chars.length()));
    }

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 10; i++) {
            try (Socket socket = new Socket("localhost", 8989);
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
                ) {
                    out.println("{ \"type\": \"ADD\", \"task\": \"task #" + pickRandomChar() + "\" }");
                    System.out.println(in.readLine());
            }
        }
    }
}
