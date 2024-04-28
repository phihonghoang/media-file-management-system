package domainLogic.administration;

import contract.Audio;
import domainLogic.media.AudioImpl;

import java.util.ArrayList;
import java.util.List;

public class AudioAdmin {
    private final List<AudioImpl> list;

    public AudioAdmin()
    {
        this.list = new ArrayList<>();
    }


    public boolean insert(AudioImpl audio)
    {
        if (audio == null)
        {
            return false;
        }
        else
        {
            list.add(audio);
            return true;
        }
    }

    public boolean delete(String location)
    {
        if (location == null)
        {
            return false;
        }

        if (list.isEmpty())
        {
            return false;
        }

        for (AudioImpl audio : list)
        {
            if (audio.getAddress().equals(location))
            {
                list.remove(audio);
                return true;
            }
        }

        return false;
    }

    public List<AudioImpl> list()
    {
        return new ArrayList<>(list);
    }

    public boolean update(String location)
    {
        if (location == null) {
            return false;
        }

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
