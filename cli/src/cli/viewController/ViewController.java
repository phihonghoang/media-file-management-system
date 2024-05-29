package cli.viewController;

import cli.inputValidator.InputValidator;
import contract.Tag;
import contract.Uploader;
import domainLogic.administration.MediaUploadableCRUD;
import domainLogic.administration.MediaUploadableMap;
import domainLogic.media.*;
import org.junit.jupiter.api.Tags;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class ViewController {

    private MediaUploadableMap model;
    private Scanner scanner;
    private InputValidator inputValidator;
    private Mode currentMode;

    public ViewController(MediaUploadableMap model) {
        this.model = model;
        this.scanner = new Scanner(System.in);
        this.inputValidator = new InputValidator();
        this.currentMode = Mode.Default;
    }

    public void execute() {

        help();

        while (true) {

            String input = userInput();

            if (input.equals(":q")) {
                break;
            }

            if (input.startsWith(":")) {

                switch (input) {
                    case ":c":
                        currentMode = Mode.Insertion;
                        break;

                    case ":d":
                        currentMode = Mode.Delete;
                        break;

                    case ":r":
                        currentMode = Mode.Display;
                        break;

                    case ":u":
                        currentMode = Mode.Update;
                        break;

                    case ":p":
                        currentMode = Mode.Persistence;
                        break;

                    case ":h":
                        currentMode = Mode.Help;
                        break;

                    default:
                        System.out.println("Invalid mode");
                        break;
                }

            } else {

                switch (currentMode) {
                    case Insertion:
                        insertionMode(input);
                        break;

                    case Delete:
                        deleteMode(input);
                        break;

                    case Display:
                        displayMode(input);
                        break;

                    case Update:
                        updateMode(input);
                        break;

                    case Persistence:
                        persistenceMode(input);
                        break;

                    case Help:
                        help();
                        break;

                    default:
                        System.out.println("Invalid input");
                        break;
                }
            }

        }
    }

    public void help() {
        System.out.println("Help:");
        System.out.println(":c switch to insertion mode");
        System.out.println(":d switch to delete mode");
        System.out.println(":r switch to display mode");
        System.out.println(":u switch to update mode");
        System.out.println(":p switch to persistence mode");
        System.out.println(":h help");
        System.out.println(":q quit");
    }

    public String userInput() {
        System.out.print("> ");
        return scanner.nextLine();
    }

    public void insertionMode(String input) {
        System.out.println("Insertion mode:");
        System.out.println("[P-Name]");
        System.out.println("[Media-Typ] [P-Name] [kommaseparierte Tags, einzelnes Komma für keine] [Größe] [Abrufkosten] [[Optionale Parameter]]");

        System.out.println(createUploader(input));
        System.out.println(validateMedia(input));
    }

    public void deleteMode(String input) {
        System.out.println("deletion mode:");
        System.out.println("[P-Name]");
        System.out.println("[Abrufadresse]");

        System.out.println(deleteUploader(input));
        System.out.println(deleteMedia(input));
    }

    public void displayMode(String input) {
        System.out.println("display mode:");
        System.out.println("uploader");
        System.out.println("content [[Typ]]");
        System.out.println("tag [enthalten(i)/nicht enthalten(e)]");

        //String[] parts = input.trim().split(" ");

        switch (input) {
            case "uploader":
                displayUploader();
                break;

            case "content Audio", "content Video", "content AudioVideo":
                displayContent(model.getMap().values(), input);
                break;

            case "tag i", "tag e":
                System.out.println("tag");
                break;

            default:
                System.out.println("Invalid input");
                break;
        }

    }

    public void updateMode(String input) {
        System.out.println("update mode:");
        System.out.println("[Abrufadresse]");
        System.out.println(updateMedia(input));
    }

    public void persistenceMode(String input) {
        System.out.println("Persistence mode:");
        System.out.println("save [JOS|JBP]");
        System.out.println("load [JOS|JBP]");
    }

    public boolean createUploader(String input) {
        String[] parts;
        //parts = input.trim().split("[ ]+");
        parts = input.trim().split("\\s+");

        if (inputValidator.uploaderValidation(parts)) {
            return model.insertUploader(parts[0], new MediaUploadableCRUD());
        }

        return false;
    }

    // Kontrolle: Bei einem falschen Tag, Long und BigDecimal
    public boolean validateMedia(String input) {
        String[] parts;
        parts = input.trim().split("\\s+");

        if (inputValidator.mediaValidation(parts) && inputValidator.numberFormatValidation(parts)) {
            Collection<Tag> list = getAddedTagList(parts[2]);
            return createMedia(parts, list);
        }

        return false;
    }

    public boolean createMedia(String[] parts, Collection<Tag> list) {
        String mediaType = parts[0];
        Uploader uploader = new UploaderImpl(parts[1]);
        long size = Long.parseLong(parts[3]);
        BigDecimal price = new BigDecimal(parts[4]);

        switch (mediaType) {
            case "Audio":
                MediaUploadableItem audio = new AudioImpl(list, size, uploader, price, 100);
                return model.insertMUI(audio.getUploader().getName(), audio);

            case "Video":
                MediaUploadableItem video = new VideoImpl(list, size, uploader, price, 100);
                return model.insertMUI(video.getUploader().getName(), video);

            case "AudioVideo":
                MediaUploadableItem audioVideo = new AudioVideoImpl(list, size, uploader, price, 100, 100);
                return model.insertMUI(audioVideo.getUploader().getName(), audioVideo);

            default:
                return false;
        }
    }

    public Collection<Tag> getAddedTagList(String input) {
        String[] parts;
        parts = input.split(",");

        Collection<Tag> list = new ArrayList<>();

        for (String part : parts) {
            for (Tag tag : Tag.values()) {
                if (part.equals(",")) {
                    break;
                }

                if (part.equals(tag.name()) && (!(list.contains(tag)))) {
                    list.add(tag);
                }
            }
        }
        return list;
    }

    public boolean deleteUploader(String uploader) {
        return model.deleteUploader(uploader);
    }

    public boolean deleteMedia(String location) {
        return model.deleteMUI(location);
    }

    public boolean displayUploader() {
        if (model.getMap().keySet().isEmpty()) {
            System.out.println("Keine Produzenten vorhanden!");
            return false;
        }

        for (String producer: model.getMap().keySet()) {
            System.out.println("Produzenten: " + producer + ", Mediadateien: " + model.getMap().get(producer).getList().size());
        }
        return true;
    }

    public boolean  displayContent(Collection<MediaUploadableCRUD> mapValues, String input) {
        if (mapValues.isEmpty()) {
            System.out.println("Empty!");
            return false;
        }

        String[] parts = input.trim().split(" ");
        String mediaType = parts[1];

        List<MediaUploadableItem> list = filterMediaType(mapValues, mediaType);

        if (list.isEmpty()) {
            System.out.println("Empty!");
            return false;
        }

        for (MediaUploadableItem item : list) {
            System.out.println(mediaType + ", Abrufadresse: " + item.getAddress() + ", Abrufe: " + item.getAccessCount());
        }
        return true;
    }

    public List<MediaUploadableItem> filterMediaType(Collection<MediaUploadableCRUD> mapValues, String mediaType) {
        List<MediaUploadableItem> list = new ArrayList<>();

        for (MediaUploadableCRUD lists : mapValues) {
            for (MediaUploadableItem items : lists.getList()) {
                if (validateMediaType(items, mediaType)) {
                    list.add(items);
                }
            }
        }

        return list;
    }

    public boolean validateMediaType(MediaUploadableItem items, String mediaType) {
        switch (mediaType) {
            case "Audio":
                if (items instanceof AudioImpl) {
                    return true;
                }
                break;
            case "Video":
                if (items instanceof VideoImpl) {
                    return true;
                }
                break;
            case "AudioVideo":
                if (items instanceof AudioVideoImpl) {
                    //System.out.println("AudioVideo" + ", Abrufadresse: " + items.getAddress() + ", Abrufe: " + items.getAccessCount());
                    return true;
                }
                break;
        }

        return false;
    }

    public void displayTag() {

    }

    public boolean updateMedia(String location) {
        return model.updateMUI(location);
    }

}
