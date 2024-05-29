package domainLogic.administration;

import domainLogic.media.MediaUploadableItem;

import java.util.HashMap;
import java.util.Map;

public class MediaUploadableMap {

    private Map<String, MediaUploadableCRUD> map;

    public MediaUploadableMap() {
        map = new HashMap<>();
    }

    public boolean insertUploader(String uploader, MediaUploadableCRUD crud) {
        if (uploader == null || crud == null) {
            return false;
        }

        if (map.containsKey(uploader)) {
            return false;
        }

        map.put(uploader, crud);
        return true;
    }

    public boolean insertMUI(String uploader, MediaUploadableItem mui) {
        if (uploader == null || mui == null) {
            return false;
        }

        if (!(map.containsKey(uploader))) {
            return false;
        }

        map.get(uploader).insert(mui);
        return true;
    }

    public boolean deleteUploader(String uploader) {
        if (uploader == null || map.isEmpty()) {
            return false;
        }

        if (!(map.containsKey(uploader))) {
            return false;
        }

        map.remove(uploader);
        return true;
    }

    public boolean deleteMUI(String location) {
        if (location == null || map.isEmpty()) {
            return false;
        }

        boolean found = false;
        for (MediaUploadableCRUD list: map.values()) {
            found = list.delete(location);
        }

        return found;
    }

    public Map<String, MediaUploadableCRUD> getMap() {
        return new HashMap<>(map);
    }

    public boolean updateMUI(String location) {
        if (location == null || map.isEmpty()) {
            return false;
        }

        boolean found = false;
        for (MediaUploadableCRUD list: map.values()) {
            found = list.update(location);
        }

        return found;
    }
}
