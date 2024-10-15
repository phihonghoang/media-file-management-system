package domainLogic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UploadableImplTest {
    private UploadableImpl uploadable;

    @BeforeEach
    void setUp() {
        uploadable = new UploadableImpl();
    }

    @Test
    public void uploadable_exists() {
        assertNotNull(uploadable);
    }

    @Test
    public void getUploader_isNull() {
        assertNull(uploadable.getUploader());
    }

    @Test
    public void getAvailability_isNull() {
        assertNull(uploadable.getAvailability());
    }

    @Test
    public  void getCost_isNull() {
        assertNull(uploadable.getCost());
    }


}