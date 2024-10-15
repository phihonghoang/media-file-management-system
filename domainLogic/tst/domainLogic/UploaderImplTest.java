package domainLogic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UploaderImplTest {
    private UploaderImpl uploader;

    @BeforeEach
    void setUp() {
        uploader = new UploaderImpl("Phi");
    }

    @Test
    public void uploader_exists() {
        assertNotNull(uploader);
    }

    @Test
    public void getName_notNull() {
        assertNotNull(uploader.getName());
        assertEquals("Phi", uploader.getName());
    }

}