package domainLogic;

import observerPatternContract.Observer;
import observerPatternContract.Subject;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MediaUploadableAdmin implements Subject, Serializable {

    private Map<String, MediaUploadableCRUD> map;
    private long maxCapacity;
    private long currentCapacity;
    @Serial
    private static final long serialVersionUID = 1L;
    private List<Observer> observerList;

    public MediaUploadableAdmin(long maxCapacity) {
        map = new HashMap<>();
        this.maxCapacity = maxCapacity;
        currentCapacity = 0;
        observerList = new LinkedList<>();
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

        if ((getMaxCapacity() - (getCurrentCapacity() + mui.getSize())) < 0) {
            return false;
        }

        setCurrentCapacity(getCurrentCapacity() + mui.getSize());
        map.get(uploader).insert(mui);
        notifyObservers();
        return true;
    }

    public boolean deleteUploader(String uploader) {
        if (uploader == null || map.isEmpty()) {
            return false;
        }

        if (!(map.containsKey(uploader))) {
            return false;
        }

        setCurrentCapacity(0);
        map.remove(uploader);
        return true;
    }

    public MediaUploadableItem deleteMUI(String location) {
        if (location == null || map.isEmpty()) {
            return null;
        }

        MediaUploadableItem muiDel = null;
        for (MediaUploadableCRUD list: map.values()) {

            MediaUploadableItem temp = list.delete(location);
            if (temp != null) {
                muiDel = temp;
                setCurrentCapacity(getCurrentCapacity() - muiDel.getSize());
                break;
            }
        }

        notifyObservers();
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
            if (list.update(location)) {
                found = true;
                break;
            }
        }

        return found;
    }

    public long getMaxCapacity() {
        return maxCapacity;
    }

    private void setCurrentCapacity(long currentCapacity) {
        this.currentCapacity = currentCapacity;
    }

    public long getCurrentCapacity() {
        return currentCapacity;
    }

    @Override
    public void registerObserver(Observer observer) {
        this.observerList.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        this.observerList.remove(observer);
    }

    @Override
    public void notifyObservers() {
        this.observerList.forEach(Observer::update);
    }
}
