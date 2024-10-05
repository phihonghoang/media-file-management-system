package net;

import eventSystem.infrastructure.DisplayUploaderEvent;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TCPClient {
    Scanner scanner;

    public TCPClient() {
        scanner = new Scanner(System.in);
    }

    public void execute() {

        try (Socket socket = new Socket("localhost", 7000);
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
             DataOutputStream out = new DataOutputStream(socket.getOutputStream());) {


            while (true) {

                System.out.println("Client input:");
                String input = scanner.nextLine();

                if (input.equals(":q")) {
                    break;
                }

                // Anfrage als String senden
                out.writeUTF(input);
                out.flush();

                Object response = in.readObject();

                if (response instanceof DisplayUploaderEvent) {
                    DisplayUploaderEvent event = (DisplayUploaderEvent) response;

                }
            }

        } catch (ConnectException e) {
            System.out.println("server unreachable");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }


    /*
    public void help() {
        System.out.println("Help:");
        System.out.println(":c [uploader|audio]");
        System.out.println(":d [uploader]");
        System.out.println(":r [uploader]");
        System.out.println(":u [abrufadresse]");
        System.out.println(":p [JOS|JBP]");
        System.out.println(":q quit");
    }

    public String userInput() {
        System.out.print("> ");
        return scanner.nextLine();
    }
     */




}
