package domainLogic.administration;

import contract.Audio;
import domainLogic.media.AudioImpl;

import java.util.ArrayList;
import java.util.List;

public class AudioAdmin {
    private final List<AudioImpl> list;

    public AudioAdmin() {
        this.list = new ArrayList<>();
    }

    public void insert(AudioImpl audio) {
        list.add(audio);
    }

    public boolean delete(String location) {
        if (list.isEmpty()) {
            return false;
        }

        return list.removeIf(audio -> audio.getAddress().equals(location));
    }

    // read-only liste zurückgeben.
    public List<Audio> list() {
        return new ArrayList<>(list);
    }

    public boolean update(String location) {
        if (list.isEmpty()) {
            return false;
        }

        for (AudioImpl audio : list) {
            if (audio.getAddress().equals(location)) {
                audio.setAccessCount(audio.getAccessCount()+1);
                return true;
            }
        }

        return false;
    }
}
