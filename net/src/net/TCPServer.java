package net;

import contract.Tag;
import contract.Uploader;
import domainLogic.MediaUploadableCRUD;
import domainLogic.MediaUploadableMap;
import domainLogic.AudioImpl;
import domainLogic.MediaUploadableItem;
import domainLogic.UploaderImpl;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;

public class TCPServer {

    MediaUploadableMap model;

    public TCPServer(MediaUploadableMap model) {
        this.model = model;
    }

    public void execute() {

        try (ServerSocket serverSocket = new ServerSocket(7000)) {
            try (Socket socket = serverSocket.accept();
                 DataInputStream in = new DataInputStream(socket.getInputStream());
                 DataOutputStream out = new DataOutputStream(socket.getOutputStream());) {

                System.out.println("client: "+socket.getInetAddress()+":"+socket.getPort());

                while (true) {
                    String clientMessage = in.readUTF();
                    System.out.println("> Request from client: " + clientMessage);
                    String res = handleRequest(clientMessage);
                    out.writeUTF(res);
                    out.flush();
                }

            } catch (EOFException e) {
                System.out.println("client disconnect");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public String handleRequest(String clientMessage) {
        String[] parts;
        parts = clientMessage.trim().split("\\s+");

        if (parts.length < 2) {
            return "error, invalid request";
        }

        switch (parts[0]) {
            case ":c":
                if (insert(parts[1])) {
                    return "success";
                } else {
                    return "error";
                }

            case ":d":
                if (delete(parts[1])) {
                    return "success";
                } else {
                    return "error";
                }

            case ":r":
                return display(parts[1]);

            case ":u":
                if (update(parts[1])) {
                    return "success";
                } else {
                    return "error";
                }

            case ":p":
                return persistence(parts[1]);

            default:
                return "Request denied: Invalid mode";
        }

    }

    public boolean insert(String input) {
        if (input.equals("uploader")) {
            return model.insertUploader("DefaultPeter", new MediaUploadableCRUD());
        } else if (input.equals("audio")) {
            Collection<Tag> list = new ArrayList<>();
            Uploader uploader = new UploaderImpl("DefaultPeter");
            long size = Long.parseLong("10");
            BigDecimal price = new BigDecimal("3.60");
            return model.insertMUI("DefaultPeter", new AudioImpl(list, size, uploader, price, 100));
        } else {
            return false;
        }
    }

    public boolean delete(String input) {
        if (input.equals("uploader")) {
            return model.deleteUploader("DefaultPeter");
        } else {
            return false;
        }
    }

    public String display(String input) {
        if (input.equals("uploader")) {
            if (model.getMap().get("DefaultPeter") == null) {
                return "error";
            }

            if (model.getMap().get("DefaultPeter").getList().isEmpty()) {
                return "error";
            }

            StringBuilder audioString = new StringBuilder();
            for (MediaUploadableItem item : model.getMap().get("DefaultPeter").getList()) {
                audioString.append("Abrufadresse: ").append(item.getAddress()).append(", ").append("Update: ").append(item.getAccessCount()).append("\n");
            }
            return audioString.toString();
        } else {
            return "error";
        }
    }

    public boolean update(String input) {
        return model.updateMUI(input);
    }

    public String persistence(String input) {
        if (input.equals("JOS")) {
            return "PLACEHOLDER: JOS";
        } else if (input.equals("JPB")) {
            return "PLACEHOLDER: JPB";
        } else {
            return "error";
        }
    }

}