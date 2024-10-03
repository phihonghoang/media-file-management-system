package domainLogic;

import contract.Tag;
import contract.Uploader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
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
        Duration availability = Duration.ZERO;
        BigDecimal cost = new BigDecimal("500");
        LocalDateTime uploadTime = LocalDateTime.now();

        audio = new AudioImpl(tags, 0, uploader, availability, cost, 500, uploadTime, "Audio");
        video = new VideoImpl(tags,  0, uploader, availability, cost, 500, uploadTime, "Video");
        audioVideo = new AudioVideoImpl( tags, 0, uploader, availability, cost, 500, 500, uploadTime, "AudioVideo");

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

        MediaUploadableItem audioDel = muCRUD.delete(audio.getAddress());
        MediaUploadableItem videoDel = muCRUD.delete(video.getAddress());
        MediaUploadableItem audioVideoDel = muCRUD.delete(audioVideo.getAddress());

        assertEquals(audio, audioDel);
        assertEquals(video, videoDel);
        assertEquals(audioVideo, audioVideoDel);
    }

    @Test
    public void delete_nonExistingLocation() {
        MediaUploadableItem mui = muCRUD.delete("1234");

        assertNull(mui);
    }

    @Test
    public void delete_null() {
        MediaUploadableItem mui = muCRUD.delete(null);

        assertNull(mui);
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