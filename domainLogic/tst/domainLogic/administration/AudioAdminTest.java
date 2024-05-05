package domainLogic.administration;

import domainLogic.media.AudioImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AudioAdminTest {

    @Test
    public void insert_notNull()
    {
        AudioAdmin admin = new AudioAdmin();
        AudioImpl audio = new AudioImpl(); // setup


        boolean result = admin.insert(audio); // untertest

        assertTrue(result); //prüfen
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