package domainLogic.administration;

import domainLogic.media.AudioImpl;
import domainLogic.media.MediaUploadableItem;

import java.util.ArrayList;
import java.util.List;

public class MediaUploadableCRUD {

    private List<MediaUploadableItem> list;

    public MediaUploadableCRUD() {
        list = new ArrayList<>();
    }

    public boolean insert(MediaUploadableItem mui) {
        if (mui == null) {
            return false;
        } else {
            return list.add(mui);
        }
    }

    // Eventuell Iterator
    public boolean delete(String location) {
        if (location == null || list.isEmpty()) {
            return false;
        }

        for (MediaUploadableItem mui : list) {
            if (mui.getAddress().equals(location)) {
                return list.remove(mui);
            }
        }

        return false;
    }

    public List<MediaUploadableItem> getList() {
        return new ArrayList<>(list);
    }

    public boolean update(String location) {
        if (location == null || list.isEmpty()) {
            return false;
        }

        for (MediaUploadableItem mui : list) {
            if (mui.getAddress().equals(location)) {
                mui.increaseAccessCount();
                return true;
            }
        }

        return false;
    }

}
