package cli.controller;

import cli.view.AudioView;
import contract.Audio;
import domainLogic.media.AudioImpl;

import java.util.Scanner;

public class AudioController {
    private Audio model;
    private AudioView view;

    public AudioController(AudioImpl model, AudioView view) {
        this.model = model;
        this.view = view;
    }

    public AudioController() {

    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        displayCommandSet();

        while (true) {

            System.out.print("> ");
            String input = scanner.nextLine();
            System.out.println();

            if (input.equals("q")) {
                break;
            }

            switch (input) {
                case "c":
                    System.out.println("success c");
                    break;

                case "d":
                    System.out.println("success d");
                    break;

                case "r":
                    System.out.println("success r");
                    break;

                case "u":
                    System.out.println("success u");
                    break;

                case "p":
                    System.out.println("success p");
                    break;

                default:
                    System.out.println("Invalid input");
                    break;
            }

            System.out.println();
        }

    }

    public void displayCommandSet() {
        System.out.println("Command set:");
        System.out.println(":c switch to insertion mode:");
        System.out.println(":d switch to delete mode:");
        System.out.println(":r switch to display mode:");
        System.out.println(":u switch to change mode:");
        System.out.println(":p switch to persistence mode:");
        System.out.println(":q quit:" + "\n");
    }
}
