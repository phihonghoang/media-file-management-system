package domainLogic.administration;

import contract.Audio;
import domainLogic.media.AudioImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class AudioAdminTest {
    /*
    @Test
    public void insert_result() {
        AudioAdmin admin = new AudioAdmin();
        Audio audio = new AudioImpl();
        String result = admin.insert(audio);
        assertNotNull(result);
    }

    @Test
    public void insert_list() {
        AudioAdmin admin = new AudioAdmin();
        Audio audio = new AudioImpl();
        String result = admin.insert(audio);
        assertEquals(1, admin.list().size());
    }

    // Schlechter Test
    @Test
    public void insert_list_contains() {
        AudioAdmin admin = new AudioAdmin();
        Audio audio = new AudioImpl();
        String result = admin.insert(audio);

        assertEquals(audio, admin.list().get(0));
    }

    @Test
    public void delete() {
        AudioAdmin admin = new AudioAdmin();
        Audio audio = new AudioImpl();
        Boolean result = admin.delete(audio.getAddress());
        assertTrue(result);
    }

    public void deleteM() {
        AudioAdmin a = new AudioAdmin();
        a.insert(mock(Audio.class));
        a.insert(mock(Audio.class));

        List<Audio> list1 = a.list();

        a.delete(null);

        assertEquals(list1, a.list().size());

    }

    @Test
    public void list() {
        AudioAdmin a = new AudioAdmin();
        a.insert(new AudioImpl());
        a.insert(new AudioImpl());

        List<Audio> r = a.list();
        r.clear();
        List<Audio> r2 = a.list();
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

    public void update() {
        fail();
    }
     */
}