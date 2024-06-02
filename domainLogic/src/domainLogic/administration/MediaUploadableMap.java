package domainLogic.administration;

import domainLogic.media.MediaUploadableItem;

import java.util.HashMap;
import java.util.Map;

public class MediaUploadableMap {

    private Map<String, MediaUploadableCRUD> map;
    private long capacity;

    public MediaUploadableMap(long capacity) {
        map = new HashMap<>();
        this.capacity = capacity;
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

        if ((getCapacity() - mui.getSize()) < 0) {
            return false;
        }

        setCapacity(getCapacity() - mui.getSize());
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

        long totalUploaderSize = 0;
        for (MediaUploadableItem items : map.get(uploader).getList()) {
            totalUploaderSize += items.getSize();
        }

        setCapacity(getCapacity() + totalUploaderSize);
        map.remove(uploader);
        return true;
    }

    public MediaUploadableItem deleteMUI(String location) {
        if (location == null || map.isEmpty()) {
            return null;
        }

        MediaUploadableItem muiDel = null;
        for (MediaUploadableCRUD list: map.values()) {
            muiDel = list.delete(location);
        }

        if (muiDel != null) {
            setCapacity(getCapacity() + muiDel.getSize());
        }

        return muiDel;
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

    private void setCapacity(long capacity) {
        this.capacity = capacity;
    }

    public long getCapacity() {
        return capacity;
    }
}
