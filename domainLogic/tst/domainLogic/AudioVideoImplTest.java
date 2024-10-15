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

class AudioVideoImplTest {
    private AudioVideoImpl audioVideo;

    @BeforeEach
    public void setUp() {
        Collection<Tag> tags = new ArrayList<>();
        Uploader uploader = new UploaderImpl("Phi");
        Duration availability = Duration.ZERO;
        BigDecimal cost = new BigDecimal("500");
        LocalDateTime uploadTime = LocalDateTime.now();

        audioVideo = new AudioVideoImpl( tags, 0, uploader, availability, cost, 500, 500, uploadTime, "AudioVideo");    }

    @Test
    public void audioVideo_exists() {
        assertNotNull(audioVideo);
    }

    @Test
    public void getSamplingRate_test() {
        assertEquals(500, audioVideo.getSamplingRate());
    }

    @Test
    public void getResolution_test() {
        assertEquals(500, audioVideo.getResolution());
    }

}