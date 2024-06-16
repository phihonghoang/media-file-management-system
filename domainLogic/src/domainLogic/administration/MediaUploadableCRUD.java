package domainLogic.administration;

import domainLogic.media.AudioImpl;
import domainLogic.media.MediaUploadableItem;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MediaUploadableCRUD implements Serializable {

    private List<MediaUploadableItem> list;
    @Serial
    private static final long serialVersionUID = 1L;

    public MediaUploadableCRUD() {
        // TODO: Keine ArrayList
        list = new ArrayList<>();
    }

    public boolean insert(MediaUploadableItem mui) {
        if (mui == null) {
            return false;
        } else {
            return list.add(mui);
        }
    }

    public MediaUploadableItem delete(String location) {
        if (location == null || list.isEmpty()) {
            return null;
        }

        for (MediaUploadableItem mui : list) {
            if (mui.getAddress().equals(location)) {
                if (list.remove(mui)) {
                    return mui;
                }
            }
        }

        return null;
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
