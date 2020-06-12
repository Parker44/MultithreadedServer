package com.parker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.Buffer;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try(Socket socket = new Socket("localhost", 5000)) {
            BufferedReader inputStream = new BufferedReader((new InputStreamReader(socket.getInputStream())));
            PrintWriter outputStream = new PrintWriter(socket.getOutputStream(), true);

            Scanner scanner = new Scanner(System.in);
            String outputString;
            String response;

            do {
                System.out.println("Enter string to be echoed: ");
                outputString = scanner.nextLine();

                outputStream.println(outputString);
                if(!outputString.equals("exit")) {
                    response = inputStream.readLine();
                    System.out.println(response);
                }

            } while(!outputString.equals("exit"));

        } catch (IOException e) {
            System.out.println("Client error " + e.getMessage());
        }
    }
}
