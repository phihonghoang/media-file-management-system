package domainLogic;

import java.io.Serial;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class MediaUploadableCRUD implements Serializable {

    private List<MediaUploadableItem> list;
    @Serial
    private static final long serialVersionUID = 1L;

    public MediaUploadableCRUD() {
        list = new LinkedList<>();
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
        return new LinkedList<>(list);
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
