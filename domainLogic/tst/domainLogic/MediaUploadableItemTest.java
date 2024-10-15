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

import static org.junit.jupiter.api.Assertions.*;

class MediaUploadableItemTest {
    private MediaUploadableItem audio;
    private MediaUploadableItem video;
    private MediaUploadableItem audioVideo;

    @BeforeEach
    void setUp() {
        Collection<Tag> tags = new ArrayList<>();
        Uploader uploader = new UploaderImpl("Phi");
        Duration availability = Duration.ZERO;
        BigDecimal cost = new BigDecimal("500");
        LocalDateTime uploadTime = LocalDateTime.now();

        audio = new AudioImpl(tags, 0, uploader, availability, cost, 500, uploadTime, "Audio");
        video = new VideoImpl(tags,  0, uploader, availability, cost, 500, uploadTime, "Video");
        audioVideo = new AudioVideoImpl( tags, 0, uploader, availability, cost, 500, 500, uploadTime, "AudioVideo");
    }

    @Test
    public void mediaUploadableItem_exists() {
        assertNotNull(audio);
        assertNotNull(video);
        assertNotNull(audioVideo);
    }

    @Test
    public void getAddress_test() {
        assertNotNull(audio.getAddress());
        assertNotNull(video.getAddress());
        assertNotNull(audioVideo.getAddress());
    }

    @Test
    public void getTags_notNull() {
        assertNotNull(audio.getTags());
        assertEquals(0, audio.getTags().size());

        assertNotNull(video.getTags());
        assertEquals(0, audio.getTags().size());

        assertNotNull(audioVideo.getTags());
        assertEquals(0, audio.getTags().size());
    }

    @Test
    public void getAccessAccount_test() {
        assertEquals(0, audio.getAccessCount());
        assertEquals(0, video.getAccessCount());
        assertEquals(0, audioVideo.getAccessCount());
    }

    @Test
    public void getSize_test() {
        assertEquals(0, audio.getSize());
        assertEquals(0, video.getSize());
        assertEquals(0, audioVideo.getSize());
    }

    @Test
    public void getUploader_notNull() {
        assertNotNull(audio.getUploader());
        assertEquals("Phi", audio.getUploader().getName());

        assertNotNull(video.getUploader());
        assertEquals("Phi", video.getUploader().getName());

        assertNotNull(audioVideo.getUploader());
        assertEquals("Phi", audioVideo.getUploader().getName());
    }

    @Test
    public void getAvailability_notNull() {
        assertNotNull(audio.getAvailability());
        assertNotNull(video.getAvailability());
        assertNotNull(audioVideo.getAvailability());
    }

    @Test
    public void getCost_notNull() {
        assertNotNull(audio.getCost());
        assertEquals(new BigDecimal("500"), audio.getCost());

        assertNotNull(video.getCost());
        assertEquals(new BigDecimal("500"), video.getCost());

        assertNotNull(audioVideo.getCost());
        assertEquals(new BigDecimal("500"), audioVideo.getCost());
    }

    @Test
    public void getUploadTime_notNull() {
        assertNotNull(audio.getUploadTime());
        assertNotNull(video.getUploadTime());
        assertNotNull(audioVideo.getUploadTime());
    }

    @Test
    public void getMediaType_notNull() {
        assertNotNull(audio.getMediaType());
        assertEquals("Audio", audio.getMediaType());

        assertNotNull(video.getMediaType());
        assertEquals("Video", video.getMediaType());

        assertNotNull(audioVideo.getMediaType());
        assertEquals("AudioVideo", audioVideo.getMediaType());
    }

    @Test
    public void increaseAccessCount_test() {
        audio.increaseAccessCount();
        assertEquals(1, audio.getAccessCount());

        video.increaseAccessCount();
        assertEquals(1, video.getAccessCount());

        audioVideo.increaseAccessCount();
        assertEquals(1, audioVideo.getAccessCount());
    }

}