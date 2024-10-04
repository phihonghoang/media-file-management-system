package cli;

import contract.Tag;
import contract.Uploader;
import domainLogic.*;
import eventSystem.infrastructure.*;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public class ViewController {

    private Scanner scanner;
    private InputValidator inputValidator;
    private Mode currentMode;
    private EventHandler<InsertUploaderEvent> insertUploaderHandler;
    private EventHandler<InsertMuiEvent> insertMuiHandler;
    private EventHandler<DeleteUploaderEvent> deleteUploaderHandler;
    private EventHandler<DeleteMuiEvent> deleteMuiHandler;
    private EventHandler<UpdateMuiEvent> updateMuiHandler;
    private EventHandler<DisplayUploaderEvent> displayUploaderHandler;
    private EventHandler<DisplayContentEvent> displayContentHandler;
    private EventHandler<DisplayTagEvent> displayTagHandler;
    private EventHandler<SaveJosEvent> saveJosHandler;
    private EventHandler<LoadJosEvent> loadJosHandler;

    public ViewController() {
        this.scanner = new Scanner(System.in);
        this.inputValidator = new InputValidator();
        this.currentMode = Mode.Default;
    }

    public void setInsertUploaderHandler(EventHandler<InsertUploaderEvent> insertUploaderHandler) {
        this.insertUploaderHandler = insertUploaderHandler;
    }

    public void setInsertMuiHandler(EventHandler<InsertMuiEvent> insertMuiHandler) {
        this.insertMuiHandler = insertMuiHandler;
    }

    public void setDeleteUploaderHandler(EventHandler<DeleteUploaderEvent> deleteUploaderHandler) {
        this.deleteUploaderHandler = deleteUploaderHandler;
    }

    public void setDeleteMuiHandler(EventHandler<DeleteMuiEvent> deleteMuiHandler) {
        this.deleteMuiHandler = deleteMuiHandler;
    }

    public void setUpdateMuiHandler(EventHandler<UpdateMuiEvent> updateMuiHandler) {
        this.updateMuiHandler = updateMuiHandler;
    }

    public void setDisplayUploaderHandler(EventHandler<DisplayUploaderEvent> displayUploaderHandler) {
        this.displayUploaderHandler = displayUploaderHandler;
    }

    public void setDisplayContentHandler(EventHandler<DisplayContentEvent> displayContentHandler) {
        this.displayContentHandler = displayContentHandler;
    }

    public void setDisplayTagHandler(EventHandler<DisplayTagEvent> displayTagHandler) {
        this.displayTagHandler = displayTagHandler;
    }

    public void setSaveJosHandler(EventHandler<SaveJosEvent> saveJosHandler) {
        this.saveJosHandler = saveJosHandler;
    }

    public void setLoadJosHandler(EventHandler<LoadJosEvent> loadJosHandler) {
        this.loadJosHandler = loadJosHandler;
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

    // TODO: Bei eingabe, muss nochmal etwas eingegeben werden damit die Ausgabe von help kommt.
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
        String[] parts;
        parts = input.trim().split("\\s+");

        if (inputValidator.uploaderValidation(parts)) {
            createUploader(parts[0]);
        } else if (inputValidator.mediaValidation(parts)){
            System.out.println(validateMedia(parts));
        } else {
            System.out.println("Invalid input");
        }
    }

    public void deleteMode(String input) {
        deleteUploader(input);
        deleteMedia(input);
    }

    public void displayMode(String input) {
        switch (input) {
            case "uploader":
                displayUploader();
                break;

            case "content Audio", "content Video", "content AudioVideo":
                displayContent(input);
                break;

            case "tag i", "tag e":
                displayTag(input);
                break;

            default:
                System.out.println("Invalid input");
                break;
        }
    }

    public void updateMode(String input) {
        updateMedia(input);
    }

    public void persistenceMode(String input) {
        switch (input) {
            case "save JOS":
                save();
                break;
            case "load JOS":
                load();
                break;
            case "save JBP":
                System.out.println("PLACEHOLDER: save JBP");
                break;
            case "load JBP":
                System.out.println("PLACEHOLDER: load JBP");
                break;
            default:
                System.out.println("Invalid input");
                break;
        }
    }

    public void createUploader(String uploader) {
        
        if (insertUploaderHandler != null) {
            InsertUploaderEvent insertUploaderEvent = new InsertUploaderEvent(this, uploader);
            insertUploaderHandler.handle(insertUploaderEvent);
        }
    }

    // TODO: Wenn ein nicht existierender Tag eingegeben wird => Fehler werfen (EVENTUELL).
    public boolean validateMedia(String[] parts) {

        if (!inputValidator.longFormatValidation(parts[3]) || !inputValidator.bigDecimalFormatValidation(parts[4])
                || !inputValidator.longPositivValidation(parts[3]) || !inputValidator.bigDecimalPositivValidation(parts[4])) {
            return false;
        }

        String mediaType = parts[0];
        Uploader uploader = new UploaderImpl(parts[1]);
        Collection<Tag> list = getAddedTagList(parts[2]);
        long size = Long.parseLong(parts[3]);
        BigDecimal price = new BigDecimal(parts[4]);
        int sampRes1 = 1010;
        int sampRes2 = 2020;
        Duration availability = Duration.ZERO;
        LocalDateTime uploadTime = LocalDateTime.now();

        if (inputValidator.sampResValidation(parts, 6)) {
            if (!inputValidator.integerFormatValidation(parts[5], "6") || !inputValidator.integerPositivValidation(parts[5], "6")) {
                return false;
            }

            sampRes1 = Integer.parseInt(parts[5]);
        }

        if (inputValidator.sampResValidation(parts, 7)) {
            if (!inputValidator.integerFormatValidation(parts[6], "7") || !inputValidator.integerPositivValidation(parts[6], "7")) {
                return false;
            }

            sampRes2 = Integer.parseInt(parts[6]);
        }

        createMedia(mediaType, uploader, list, size, availability, price, sampRes1, sampRes2, uploadTime);
        return true;
    }

    public void createMedia(String mediaType, Uploader uploader, Collection<Tag> list, long size,Duration availability, BigDecimal price, int sampRes1, int sampRes2, LocalDateTime uploadTime) {

        if (insertMuiHandler != null) {
            InsertMuiEvent insertMuiEvent = new InsertMuiEvent(this, mediaType, uploader, list, size, availability, price, sampRes1, sampRes2, uploadTime);
            insertMuiHandler.handle(insertMuiEvent);
        }
    }

    // TODO: Wenn ein nicht existierender Tag eingegeben wird => Fehler werfen (EVENTUELL).
    public Collection<Tag> getAddedTagList(String input) {
        String[] parts;
        parts = input.split(",");

        Collection<Tag> list = new LinkedList<>();

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

    public void deleteUploader(String uploader) {

        if (deleteUploaderHandler != null) {
            DeleteUploaderEvent deleteUploaderEvent = new DeleteUploaderEvent(this, uploader);
            deleteUploaderHandler.handle(deleteUploaderEvent);
        }
    }

    public void deleteMedia(String location) {

        if (deleteMuiHandler != null) {
            DeleteMuiEvent deleteMuiEvent = new DeleteMuiEvent(this, location);
            deleteMuiHandler.handle(deleteMuiEvent);
        }
    }

    public void displayUploader() {

        if ( displayUploaderHandler != null) {
            DisplayUploaderEvent displayUploaderEvent = new DisplayUploaderEvent(this);
            displayUploaderHandler.handle(displayUploaderEvent);
        }
    }

    public void  displayContent(String input) {
        String[] parts = input.trim().split(" ");
        String mediaType = parts[1];

        if (displayContentHandler != null) {
            DisplayContentEvent displayContentEvent = new DisplayContentEvent(this, mediaType);
            displayContentHandler.handle(displayContentEvent);
        }
    }

    public void displayTag(String input) {
        String[] parts = input.trim().split(" ");
        String tagIE = parts[1];

        if (displayTagHandler != null) {
            DisplayTagEvent displayTagEvent = new DisplayTagEvent(this, tagIE);
            displayTagHandler.handle(displayTagEvent);
        }
    }

    public void updateMedia(String location) {

        if (updateMuiHandler != null) {
            UpdateMuiEvent updateMuiEvent = new UpdateMuiEvent(this, location);
            updateMuiHandler.handle(updateMuiEvent);
        }
    }


    public void save() {

        if (saveJosHandler != null) {
            SaveJosEvent saveJosEvent = new SaveJosEvent(this);
            saveJosHandler.handle(saveJosEvent);
        }
    }


    public void load() {

        if (loadJosHandler != null) {
            LoadJosEvent loadJosEvent = new LoadJosEvent(this);
            loadJosHandler.handle(loadJosEvent);
        }
    }

}
