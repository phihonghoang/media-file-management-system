package cli;

import contract.Tag;
import contract.Uploader;
import domainLogic.*;
import eventSystem.infrastructure.*;
import io.MediaUploadablePersistence;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.*;

public class ViewController {

    private MediaUploadableAdmin model;
    private Scanner scanner;
    private InputValidator inputValidator;
    private Mode currentMode;
    private MediaUploadablePersistence persistence;
    private EventHandler<InsertUploaderEvent> insertUploaderHandler;
    private EventHandler<InsertMuiEvent> insertMuiHandler;
    private EventHandler<DeleteUploaderEvent> deleteUploaderHandler;
    private EventHandler<DeleteMuiEvent> deleteMuiHandler;
    private EventHandler<UpdateMuiEvent> updateMuiHandler;
    private EventHandler<DisplayUploaderEvent> displayUploaderHandler;

    public ViewController(MediaUploadableAdmin model, MediaUploadablePersistence persistence) {
        this.model = model;
        this.scanner = new Scanner(System.in);
        this.inputValidator = new InputValidator();
        this.currentMode = Mode.Default;
        this.persistence = persistence;
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
                displayContent(model.getMap().values(), input);
                break;

            case "tag i", "tag e":
                displayTag(model.getMap().values(), input);
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

        InsertUploaderEvent insertUploaderEvent = new InsertUploaderEvent(this, uploader);
        insertUploaderHandler.handle(insertUploaderEvent);
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

        createMedia(mediaType, uploader, list, size, availability, price, sampRes1, sampRes2);
        return true;
    }

    public void createMedia(String mediaType, Uploader uploader, Collection<Tag> list, long size,Duration availability, BigDecimal price, int sampRes1, int sampRes2) {

        InsertMuiEvent insertMuiEvent = new InsertMuiEvent(this, mediaType, uploader, list, size, availability, price, sampRes1, sampRes2);
        insertMuiHandler.handle(insertMuiEvent);
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

        DeleteUploaderEvent deleteUploaderEvent = new DeleteUploaderEvent(this, uploader);
        deleteUploaderHandler.handle(deleteUploaderEvent);
    }

    public void deleteMedia(String location) {

        DeleteMuiEvent deleteMuiEvent = new DeleteMuiEvent(this, location);
        deleteMuiHandler.handle(deleteMuiEvent);
    }

    public void displayUploader() {

        DisplayUploaderEvent displayUploaderEvent = new DisplayUploaderEvent(this);
        displayUploaderHandler.handle(displayUploaderEvent);
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
            System.out.println(mediaType + ", Abrufadresse: "  + item.getAddress() +  ", Verfuegbarkeit: " + item.getAvailability().plus(updateDuration(item.getUploadTime())).getSeconds() + " Sekunden, Abrufe: " + item.getAccessCount());
        }
        return true;
    }

    public List<MediaUploadableItem> filterMediaType(Collection<MediaUploadableCRUD> mapValues, String mediaType) {
        List<MediaUploadableItem> list = new LinkedList<>();

        for (MediaUploadableCRUD muCrud : mapValues) {
            for (MediaUploadableItem items : muCrud.getList()) {
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
                    return true;
                }
                break;
        }
        return false;
    }

    public boolean displayTag(Collection<MediaUploadableCRUD> mapValues, String input) {
        String[] parts = input.trim().split(" ");
        String tagIE = parts[1];

        Set<Tag> currentTags = filterTagI(mapValues);

        String result;
        if (tagIE.equals("i")) {
            result = String.join(", ", currentTags.toString());
        } else {
            Set<Tag> unusedTags = filterTagE(currentTags);
            result = String.join(", ", unusedTags.toString());
        }

        System.out.println(result);
        return true;
    }

    public Set<Tag> filterTagI(Collection<MediaUploadableCRUD> mapValues) {
        Set<Tag> currentTags = new HashSet<>();

        for (MediaUploadableCRUD muCrud : mapValues) {
            for (MediaUploadableItem items : muCrud.getList()) {
                currentTags.addAll(items.getTags());
            }
        }

        return currentTags;
    }

    public Set<Tag> filterTagE(Collection<Tag> currentTags) {
        Set<Tag> unusedTags = new HashSet<>();

        for (Tag tag : Tag.values()) {
            if (!(currentTags.contains(tag))) {
                unusedTags.add(tag);
            }
        }

        return unusedTags;
    }

    public void updateMedia(String location) {

        UpdateMuiEvent updateMuiEvent = new UpdateMuiEvent(this, location);
        updateMuiHandler.handle(updateMuiEvent);
    }

    public Duration updateDuration(LocalTime uploadTime) {
        LocalTime now = LocalTime.now();

        return Duration.between(uploadTime, now);
    }


    public void save() {
        String filename = "MediaUploadable.jos";
        persistence.save(filename, model);
    }


    public MediaUploadableAdmin load() {
        String filename = "MediaUploadable.jos";

        MediaUploadableAdmin mapPersistence = persistence.load(filename);

        if (mapPersistence != null) {
            setModel(mapPersistence);
        }

        return mapPersistence;
    }

    private void setModel(MediaUploadableAdmin mapPersistence) {
        this.model = mapPersistence;
    }
}
