package cli;

import domainLogic.administration.MediaUploadableCRUD;

import java.util.Scanner;

public class ViewController {

    private MediaUploadableCRUD model;
    private Scanner scanner;

    public ViewController(MediaUploadableCRUD model) {
        this.model = model;
        this.scanner = new Scanner(System.in);
    }

    public void execute() {

        displayCommandSet();

        while (true) {

            String input = userInput();

            if (input.equals(":q")) {
                break;
            }

            switch (input) {
                case ":c":
                    insertionMode();
                    break;

                case ":d":
                    deleteMode();
                    break;

                case ":r":
                    displayMode();
                    break;

                case ":u":
                    updateMode();
                    break;

                case ":p":
                    persistenceMode();
                    break;

                case ":s":
                    displayCommandSet();
                    break;

                default:
                    System.out.println("Invalid input");
                    break;
            }

        }


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

    public String userInput() {
        System.out.print("> ");
        return scanner.nextLine();
    }

    public void insertionMode() {
        System.out.println("Insertion mode");
    }

    public void deleteMode() {
        System.out.println("deletion mode");
    }

    public void displayMode() {
        System.out.println("display mode");
    }

    public void updateMode() {
        System.out.println("update mode");
    }

    public void persistenceMode() {
        System.out.println("Persistence mode");
    }
}
