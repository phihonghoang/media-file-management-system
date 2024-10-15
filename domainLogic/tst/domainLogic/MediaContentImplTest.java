package domainLogic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MediaContentImplTest {
    private MediaContentImpl mediaContent;

    @BeforeEach
    void setUp() {
        mediaContent = new MediaContentImpl();
    }

    @Test
    public void mediaContent_exists() {
        assertNotNull(mediaContent);
    }

    @Test
    public void getAddress_notNull() {
        assertNotNull(mediaContent.getAddress());
        assertEquals("", mediaContent.getAddress());
    }

    @Test
    public void getTags_notNull() {
        assertNotNull(mediaContent.getTags());
        assertEquals(0, mediaContent.getTags().size());
    }

    @Test
    public void getSize_test() {
        assertEquals(0, mediaContent.getSize());
    }

}