package eventSystem.util;

import domainLogic.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class DisplayContentUtil {

    public List<MediaUploadableItem> filterMediaType(String mediaType, Collection<MediaUploadableCRUD> mapValues) {
        List<MediaUploadableItem> list = new LinkedList<>();

        for (MediaUploadableCRUD muCrud : mapValues) {
            for (MediaUploadableItem items : muCrud.getList()) {
                if (validateMediaType(mediaType, items)) {
                    list.add(items);
                }
            }
        }

        return list;
    }

    private boolean validateMediaType(String mediaType, MediaUploadableItem items) {
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

    public Duration updateDuration(LocalDateTime uploadTime) {
        LocalDateTime now = LocalDateTime.now();

        return Duration.between(uploadTime, now);
    }


}
