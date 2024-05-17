package domainLogic.administration;

import contract.Tag;
import contract.Uploader;
import domainLogic.media.AudioImpl;
import domainLogic.media.MediaUploadableItem;
import domainLogic.media.UploaderImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MediaUploadableCRUDTest {

    private MediaUploadableItem audio;
    private MediaUploadableItem video;
    private MediaUploadableItem audioVideo;
    private MediaUploadableCRUD muCRUD;


    @BeforeEach
    public void setUp() {
        Collection<Tag> tags = new ArrayList<>();
        Uploader uploader = new UploaderImpl("Phi");
        BigDecimal cost = new BigDecimal("500");

        audio = new AudioImpl("addressAudio", tags, 0, 0, uploader, cost, 500);
        video = new AudioImpl("addressVideo", tags, 0, 0, uploader, cost, 500);
        audioVideo = new AudioImpl("addressAudioVideo", tags, 0, 0, uploader, cost, 500);

        muCRUD = new MediaUploadableCRUD();
    }

    @Test
    public void insert_success() {
        boolean audioRes = muCRUD.insert(audio);
        boolean videoRes = muCRUD.insert(video);
        boolean audioVideoRes = muCRUD.insert(audioVideo);

        assertTrue(audioRes);
        assertTrue(videoRes);
        assertTrue(audioVideoRes);
    }

    @Test
    public void insert_null() {
        boolean res = muCRUD.insert(null);

        assertFalse(res);
    }

    @Test
    public void delete_success() {
        muCRUD.insert(audio);
        muCRUD.insert(video);
        muCRUD.insert(audioVideo);

        boolean audioRes = muCRUD.delete(audio.getAddress());
        boolean videoRes = muCRUD.delete(video.getAddress());
        boolean audioVideoRes = muCRUD.delete(audioVideo.getAddress());

        assertTrue(audioRes);
        assertTrue(videoRes);
        assertTrue(audioVideoRes);
    }

    @Test
    public void delete_nonExistingLocation() {
        boolean res = muCRUD.delete("1234");

        assertFalse(res);
    }

    @Test
    public void delete_null() {
        boolean res = muCRUD.delete(null);

        assertFalse(res);
    }

    @Test
    public void getList_exist() {
        List<MediaUploadableItem> muiList = muCRUD.getList();

        assertEquals(0, muiList.size());
    }

    @Test
    public void getList_insert() {
        muCRUD.insert(audio);
        muCRUD.insert(video);
        muCRUD.insert(audioVideo);

        List<MediaUploadableItem> muiList = muCRUD.getList();

        assertEquals(3, muiList.size());
    }

    @Test
    public void update_success() {
        muCRUD.insert(audio);
        muCRUD.insert(video);
        muCRUD.insert(audioVideo);

        boolean audioRes = muCRUD.update(audio.getAddress());
        boolean videoRes = muCRUD.update(video.getAddress());
        boolean audioVideoRes = muCRUD.update(audioVideo.getAddress());

        assertTrue(audioRes);
        assertTrue(videoRes);
        assertTrue(audioVideoRes);
    }

    @Test
    public void update_accessAccount_success() {
        muCRUD.insert(audio);
        muCRUD.insert(video);
        muCRUD.insert(audioVideo);

        muCRUD.update(audio.getAddress());
        muCRUD.update(video.getAddress());
        muCRUD.update(audioVideo.getAddress());

        assertEquals(1, audio.getAccessCount());
        assertEquals(1, video.getAccessCount());
        assertEquals(1, audioVideo.getAccessCount());
    }

    @Test
    public void update_null() {
        boolean res = muCRUD.update(null);

        assertFalse(res);
    }

    @Test
    public void update_nonExistingLocation() {
        boolean res = muCRUD.update("1234");

        assertFalse(res);
    }

}