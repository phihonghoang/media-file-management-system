package domainLogic.administration;

import domainLogic.media.AudioImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class AudioAdminTest {
    /*
    public void deleteM() {
        AudioAdmin a = new AudioAdmin();
        a.insert(mock(Audio.class));
        a.insert(mock(Audio.class));

        List<Audio> list1 = a.list();

        a.delete(null);

        assertEquals(list1, a.list().size());

    }

    @Test
    public void update_mockito() {
        AudioAdmin a = new AudioAdmin();
        Audio audio = mock(Audio.class);
        a.insert(new AudioImpl());

        //a.update("0");

        //assertEquals(1,audio.getAccessCount());
        //verify(audio).setAccessCount(1);
    }
     */

    @Test
    public void insert_notNull()
    {
        AudioAdmin admin = new AudioAdmin();
        AudioImpl audio = new AudioImpl();
        boolean result = admin.insert(audio);

        assertTrue(result);
    }

    @Test
    public void insert_null()
    {
        AudioAdmin admin = new AudioAdmin();
        boolean result = admin.insert(null);

        assertFalse(result);
    }

    @Test
    public void delete_notNull()
    {
        AudioAdmin admin = new AudioAdmin();
        AudioImpl audio = new AudioImpl();
        admin.insert(audio);
        boolean result = admin.delete(audio.getAddress());

        assertTrue(result);
    }

    @Test
    public void delete_null() {
        AudioAdmin admin = new AudioAdmin();
        boolean result = admin.delete(null);

        assertFalse(result);
    }

    @Test
    public void list_exist()
    {
        AudioAdmin admin = new AudioAdmin();
        List<AudioImpl> audioList = admin.list();

        assertEquals(0, audioList.size());
    }

    @Test
    public void list_insert()
    {
        AudioAdmin admin = new AudioAdmin();
        AudioImpl audio = new AudioImpl();
        admin.insert(audio);
        List<AudioImpl> audioList = admin.list();

        assertEquals(1, audioList.size());
    }

    @Test
    public void update_notNull()
    {
        AudioAdmin admin = new AudioAdmin();
        AudioImpl audio = new AudioImpl();
        admin.insert(audio);
        boolean result = admin.update(audio.getAddress());

        assertTrue(result);
        assertEquals(1,audio.getAccessCount());
    }

    @Test
    public void update_null()
    {
        AudioAdmin admin = new AudioAdmin();
        AudioImpl audio = new AudioImpl();
        boolean result = admin.update(null);

        assertFalse(result);
        assertEquals(0,audio.getAccessCount());
    }
}