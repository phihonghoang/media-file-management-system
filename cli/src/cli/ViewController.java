package cli;

import contract.Tag;
import contract.Uploader;
import domainLogic.*;
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

    public ViewController(MediaUploadableAdmin model, MediaUploadablePersistence persistence) {
        this.model = model;
        this.scanner = new Scanner(System.in);
        this.inputValidator = new InputValidator();
        this.currentMode = Mode.Default;
        this.persistence = persistence;
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
            System.out.println(createUploader(parts[0]));
        } else if (inputValidator.mediaValidation(parts)){
            System.out.println(validateMedia(parts));
        } else {
            System.out.println("Invalid input");
        }
    }

    public void deleteMode(String input) {
        System.out.println(deleteUploader(input));
        System.out.println(deleteMedia(input));
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
        System.out.println(updateMedia(input));
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

    public boolean createUploader(String uploader) {
        return model.insertUploader(uploader, new MediaUploadableCRUD());
    }

    // TODO: Wenn die Zahlen negativ sind, Fehlermeldung zurückgeben lassen.
    public boolean validateMedia(String[] parts) {

        if (!inputValidator.longFormatValidation(parts[3]) || !inputValidator.bigDecimalFormatValidation(parts[4])) {
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
            if (!inputValidator.integerFormatValidation(parts[5], "6")) {
                return false;
            }
            sampRes1 = Integer.parseInt(parts[5]);
        }

        if (inputValidator.sampResValidation(parts, 7)) {
            if (!inputValidator.integerFormatValidation(parts[6], "7")) {
                return false;
            }
            sampRes2 = Integer.parseInt(parts[6]);
        }

        return createMedia(mediaType, uploader, list, size, availability, price, sampRes1, sampRes2);
    }

    // TODO: Standard werte für samplingRate und resolution setzen, falls vom User nicht anders angegeben
    public boolean createMedia(String mediaType, Uploader uploader, Collection<Tag> list, long size,Duration availability, BigDecimal price, int sampRes1, int sampRes2) {

        switch (mediaType) {
            case "Audio":
                // MUI darf die CLI nicht kenne, stattdessen die Eigenschaften übergeben - Instanz Kontrolle.
                // Objekt erstellung im Model.
                MediaUploadableItem audio = new AudioImpl(list, size, uploader, availability, price, sampRes1);
                return model.insertMUI(audio.getUploader().getName(), audio);

            case "Video":
                MediaUploadableItem video = new VideoImpl(list, size, uploader, availability, price, sampRes1);
                return model.insertMUI(video.getUploader().getName(), video);

            case "AudioVideo":
                MediaUploadableItem audioVideo = new AudioVideoImpl(list, size, uploader, availability, price, sampRes1, sampRes2);
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

    public MediaUploadableItem deleteMedia(String location) {
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
            System.out.println(mediaType + ", Abrufadresse: "  + item.getAddress() +  ", Verfuegbarkeit: " + item.getAvailability().plus(updateDuration(item.getUploadTime())).getSeconds() + " Sekunden, Abrufe: " + item.getAccessCount());
        }
        return true;
    }

    public List<MediaUploadableItem> filterMediaType(Collection<MediaUploadableCRUD> mapValues, String mediaType) {
        List<MediaUploadableItem> list = new ArrayList<>();

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

        Collection<Tag> listI = filterTagI(mapValues);

        String result;
        if (tagIE.equals("i")) {
            result = String.join(", ", listI.toString());
        } else {
            Collection<Tag> listE = filterTagE(listI);
            result = String.join(", ", listE.toString());
        }

        System.out.println(result);
        return true;
    }

    public Collection<Tag> filterTagI(Collection<MediaUploadableCRUD> mapValues) {
        Collection<Tag> listI = new LinkedList<>();

        for (MediaUploadableCRUD muCrud : mapValues) {
            for (MediaUploadableItem items : muCrud.getList()) {
                for (Tag itemTag : items.getTags()) {
                    for (Tag tag : Tag.values()) {

                        if (itemTag.equals(tag) && (!(listI.contains(tag)))) {
                            listI.add(tag);
                        }

                    }
                }
            }
        }

        return listI;
    }

    public Collection<Tag> filterTagE(Collection<Tag> listI) {
        Collection<Tag> listE = new LinkedList<>();

        for (Tag tag : Tag.values()) {
            if (!(listI.contains(tag))) {
                listE.add(tag);
            }
        }

        return listE;
    }

    public boolean updateMedia(String location) {
        return model.updateMUI(location);
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
