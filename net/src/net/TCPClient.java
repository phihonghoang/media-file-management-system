package net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
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
             DataInputStream in = new DataInputStream(socket.getInputStream());
             DataOutputStream out = new DataOutputStream(socket.getOutputStream());) {

            help();

            while (true) {

                String input = userInput();

                if (input.equals(":q")) {
                    break;
                }

                out.writeUTF(input);
                out.flush();

                System.out.println(in.readUTF());

            }

        } catch (ConnectException e) {
            System.out.println("server unreachable");
        } catch (IOException e) {
            e.printStackTrace();
        }

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
