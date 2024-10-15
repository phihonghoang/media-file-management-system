package net;

import cli.Mode;
import eventSystem.infrastructure.DisplayUploaderEvent;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Scanner;

public class TCPClient {
    private Scanner scanner;
    private Mode currentMode;

    public TCPClient() {
        this.scanner = new Scanner(System.in);
        this.currentMode = Mode.Default;
    }

    public void execute() {

        try (Socket socket = new Socket("localhost", 7000);
             DataInputStream in = new DataInputStream(socket.getInputStream());
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());) {

            help();

            while (true) {

                userInput();

                if (userInput().equals(":q")) {
                    break;
                }

            }

        } catch (ConnectException e) {
            System.out.println("server unreachable");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void insertionMode (String input) {

    }

    public void deleteMode (String input) {

    }

    public void displayMode(String input) {

    }

    public void updateMode (String input) {

    }

    public void persistenceMode (String input) {

    }


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

}
