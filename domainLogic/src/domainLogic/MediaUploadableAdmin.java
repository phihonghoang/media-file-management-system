package domainLogic;

import contract.Tag;
import contract.Uploader;
import observerPatternContract.Observer;
import observerPatternContract.Subject;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.*;

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

    public boolean insertUploader(String uploader) {
        if (uploader == null) {
            return false;
        }

        if (map.containsKey(uploader)) {
            return false;
        }

        map.put(uploader, new MediaUploadableCRUD());
        return true;
    }

    public boolean insertMui(String mediaType, Uploader uploader, Collection<Tag> list, long size, Duration availability, BigDecimal price, int sampRes1, int sampRes2) {
        if (uploader == null) {
            return false;
        }

        if (!(map.containsKey(uploader.getName()))) {
            return false;
        }

        if ((getMaxCapacity() - (getCurrentCapacity() + size)) < 0) {
            return false;
        }

        setCurrentCapacity(getCurrentCapacity() + size);

        MediaUploadableItem mui;

        //TODO: Eventuell prüfung der MedienDateien hier verlagern.
        switch (mediaType) {
            case "Audio":
                mui = new AudioImpl(list, size, uploader, availability, price, sampRes1);
                break;
            case "Video":
                mui = new VideoImpl(list, size, uploader, availability, price, sampRes1);
                break;
            case "AudioVideo":
                mui  = new AudioVideoImpl(list, size, uploader, availability, price, sampRes1, sampRes2);
                break;
            default:
                return false;
        }

        map.get(uploader.getName()).insert(mui);
        notifyObservers();
        return true;
    }

    // TODO: Observer nochmal anschauen, wenn Uploader gelöscht keine Benachrichtigung von gelöschten Tags
    public boolean deleteUploader(String uploader) {
        if (uploader == null || map.isEmpty()) {
            return false;
        }

        if (!(map.containsKey(uploader))) {
            return false;
        }

        setCurrentCapacity(0);
        map.remove(uploader);
        notifyObservers();
        return true;
    }

    public MediaUploadableItem deleteMui(String location) {
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
