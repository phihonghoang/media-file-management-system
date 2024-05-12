package cli.controller;

import cli.view.AudioView;
import contract.Audio;
import domainLogic.administration.AudioAdmin;
import domainLogic.media.AudioImpl;
import domainLogic.media.UploaderImpl;

import java.util.Scanner;

public class AudioController {
    private AudioAdmin model;
    private AudioView view;
    private Scanner scanner;

    public AudioController(AudioAdmin model, AudioView view) {
        this.model = model;
        this.view = view;
        this.scanner = new Scanner(System.in);
    }

    public AudioController() {
        this.scanner = new Scanner(System.in);
    }

    public void start() {

        displayCommandSet();

        while (true) {

            String csInput = userInput();
            System.out.println();

            if (csInput.equals("q")) {
                break;
            }

            switch (csInput) {
                case "c":
                    insertionMode();
                    break;

                case "d":
                    deleteMode();
                    break;

                case "r":
                    displayMode();
                    break;

                case "u":
                    updateMode();
                    break;

                case "p":
                    persistenceMode();
                    break;

                case "s":
                    displayCommandSet();
                    break;

                default:
                    System.out.println("Invalid input");
                    break;
            }

            System.out.println();
        }

    }

    public String userInput() {
        System.out.print("> ");
        return scanner.nextLine();
    }

    public void displayCommandSet() {
        System.out.println("Command set");
        System.out.println(":c switch to insertion mode");
        System.out.println(":d switch to delete mode");
        System.out.println(":r switch to display mode");
        System.out.println(":u switch to update mode");
        System.out.println(":p switch to persistence mode");
        System.out.println(":s show command set");
        System.out.println(":q quit");
    }

    public void insertionMode() {
        System.out.println("Uploader:");
        String uploader = userInput();
        System.out.println("Address:");
        String address = userInput();
        AudioImpl audio = new AudioImpl(new UploaderImpl(uploader), address);

        //String media = userInput();
        //String[] splitMedia = media.trim().split("\\s+");

        boolean resultInsertion = model.insert(audio);
        view.insertion(resultInsertion);
    }

    public void deleteMode() {
        System.out.println("Address:");
        String address = userInput();
        boolean resultDelete = model.delete(address);
        view.delete(resultDelete);
    }

    public void displayMode() {
        view.display(model.list());
    }

    public void updateMode() {
        System.out.println("Address");
        String address = userInput();
        boolean updateResult = model.update(address);
        view.update(updateResult);
    }

    public void persistenceMode() {
        view.persistence();
    }

}
