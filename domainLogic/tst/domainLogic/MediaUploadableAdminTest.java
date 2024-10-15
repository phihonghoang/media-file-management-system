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

class MediaUploadableAdminTest {
    private MediaUploadableItem audio;
    private MediaUploadableItem video;
    private MediaUploadableItem audioVideo;
    private MediaUploadableAdmin model;
    private Uploader uploader1;
    private Uploader uploader2;
    private Collection<Tag> tags;
    private long size;
    private Duration availability;
    private BigDecimal cost;
    private int sampRes1;
    private int sampRes2;
    private LocalDateTime uploadTime;

    @BeforeEach
    public void setUp() {
        uploader1 = new UploaderImpl("Phi");
        uploader2 = new UploaderImpl("Thuy");
        tags = new ArrayList<>();
        size = 100;
        availability = Duration.ZERO;
        cost = new BigDecimal("500");
        sampRes1 = 500;
        sampRes2 = 500;
        uploadTime = LocalDateTime.now();

        model = new MediaUploadableAdmin(1000);
    }

    @Test
    public void model_exists() {
        assertNotNull(model);
    }

    @Test
    public void insertUploader_success() {
        assertTrue(model.insertUploader(uploader1.getName()));
        assertEquals(1, model.getMap().keySet().size());
        assertEquals(0,model.getMap().get(uploader1.getName()).getList().size());
    }

    @Test
    public void insertUploader_alreadyExists() {
        assertTrue(model.insertUploader(uploader1.getName()));
        assertFalse(model.insertUploader(uploader1.getName()));
        assertEquals(1, model.getMap().keySet().size());
    }

    @Test
    public void insertUploader_isNull() {
        assertFalse(model.insertUploader(null));
    }

    @Test
    public void insertMui_success() {
        assertTrue(model.insertUploader(uploader1.getName()));

        assertTrue(model.insertMui("Audio", uploader1, tags, size,
                availability, cost, sampRes1, sampRes2, uploadTime));

        assertTrue(model.insertMui("Video", uploader1, tags, size,
                availability, cost, sampRes1, sampRes2, uploadTime));

        assertTrue(model.insertMui("AudioVideo", uploader1, tags, size,
                availability, cost, sampRes1, sampRes2, uploadTime));

        assertEquals(1, model.getMap().keySet().size());
        assertEquals(3,model.getMap().get(uploader1.getName()).getList().size());
    }

    @Test
    public void insertMui_uploaderDoesNotExist() {
        assertTrue(model.insertUploader(uploader1.getName()));

        assertFalse(model.insertMui("Audio", uploader2, tags, size,
                availability, cost, sampRes1, sampRes2, uploadTime));

        assertFalse(model.insertMui("Video", uploader2, tags, size,
                availability, cost, sampRes1, sampRes2, uploadTime));

        assertFalse(model.insertMui("AudioVideo", uploader2, tags, size,
                availability, cost, sampRes1, sampRes2, uploadTime));

        assertEquals(0,model.getMap().get(uploader1.getName()).getList().size());
    }

    @Test
    public void insertMui_noExistingMediaType() {
        assertTrue(model.insertUploader(uploader1.getName()));

        assertFalse(model.insertMui("AV", uploader1, tags, size,
                availability, cost, sampRes1, sampRes2, uploadTime));

        assertEquals(0,model.getMap().get(uploader1.getName()).getList().size());
    }

    @Test
    public void deleteUploader_success() {
        assertTrue(model.insertUploader(uploader1.getName()));
        assertEquals(1, model.getMap().keySet().size());

        assertTrue(model.deleteUploader(uploader1.getName()));
        assertEquals(0, model.getMap().keySet().size());
    }

    @Test
    public void deleteUploader_successWithMediaFiles() {
        assertTrue(model.insertUploader(uploader1.getName()));
        assertEquals(1, model.getMap().keySet().size());

        assertTrue(model.insertMui("Audio", uploader1, tags, size,
                availability, cost, sampRes1, sampRes2, uploadTime));

        assertTrue(model.insertMui("Video", uploader1, tags, size,
                availability, cost, sampRes1, sampRes2, uploadTime));

        assertTrue(model.insertMui("AudioVideo", uploader1, tags, size,
                availability, cost, sampRes1, sampRes2, uploadTime));

        assertEquals(3,model.getMap().get(uploader1.getName()).getList().size());

        assertTrue(model.deleteUploader(uploader1.getName()));
        assertEquals(0, model.getMap().values().size());
        assertEquals(0, model.getMap().keySet().size());
    }

    @Test
    public void deleteUploader_noExistingUploader() {
        assertTrue(model.insertUploader(uploader1.getName()));

        assertFalse(model.deleteUploader(uploader2.getName()));

        assertEquals(1, model.getMap().keySet().size());
    }

    @Test
    public void deleteMui_success() {
        assertTrue(model.insertUploader(uploader1.getName()));

        assertTrue(model.insertMui("Audio", uploader1, tags, size,
                availability, cost, sampRes1, sampRes2, uploadTime));

        assertTrue(model.insertMui("Video", uploader1, tags, size,
                availability, cost, sampRes1, sampRes2, uploadTime));

        assertTrue(model.insertMui("AudioVideo", uploader1, tags, size,
                availability, cost, sampRes1, sampRes2, uploadTime));

        assertEquals(3,model.getMap().get(uploader1.getName()).getList().size());


        assertNotNull(model.deleteMui(model.getMap().get(uploader1.getName()).getList().get(0).getAddress()));
        assertNotNull(model.deleteMui(model.getMap().get(uploader1.getName()).getList().get(0).getAddress()));
        assertNotNull(model.deleteMui(model.getMap().get(uploader1.getName()).getList().get(0).getAddress()));

        assertEquals(0,model.getMap().get(uploader1.getName()).getList().size());
    }

    @Test
    public void deleteMui_noUploader() {
        assertNull(model.deleteMui(""));

        assertEquals(0, model.getMap().values().size());
    }

    @Test
    public void deleteMui_noExistingLocation() {
        assertTrue(model.insertUploader(uploader1.getName()));

        assertTrue(model.insertMui("Audio", uploader1, tags, size,
                availability, cost, sampRes1, sampRes2, uploadTime));

        assertNull(model.deleteMui(""));

        assertEquals(1,model.getMap().get(uploader1.getName()).getList().size());
    }

    @Test
    public void getMap_isNotNull() {
        assertNotNull(model.getMap());
    }

    @Test
    public void getMap_size() {
        assertTrue(model.insertUploader(uploader1.getName()));
        assertTrue(model.insertUploader(uploader2.getName()));

        assertTrue(model.insertMui("Audio", uploader1, tags, size,
                availability, cost, sampRes1, sampRes2, uploadTime));

        assertTrue(model.insertMui("Video", uploader1, tags, size,
                availability, cost, sampRes1, sampRes2, uploadTime));

        assertTrue(model.insertMui("AudioVideo", uploader1, tags, size,
                availability, cost, sampRes1, sampRes2, uploadTime));


        assertEquals(2, model.getMap().keySet().size());

        assertEquals(3,model.getMap().get(uploader1.getName()).getList().size());
        assertEquals(0,model.getMap().get(uploader2.getName()).getList().size());
    }

    @Test
    public void updateMui_success() {
        assertTrue(model.insertUploader(uploader1.getName()));

        assertTrue(model.insertMui("Audio", uploader1, tags, size,
                availability, cost, sampRes1, sampRes2, uploadTime));

        assertTrue(model.updateMui(model.getMap().get(uploader1.getName()).getList().get(0).getAddress()));
        assertTrue(model.updateMui(model.getMap().get(uploader1.getName()).getList().get(0).getAddress()));
        assertTrue(model.updateMui(model.getMap().get(uploader1.getName()).getList().get(0).getAddress()));
        assertTrue(model.updateMui(model.getMap().get(uploader1.getName()).getList().get(0).getAddress()));

        assertEquals(4,model.getMap().get(uploader1.getName()).getList().get(0).getAccessCount());
    }

    @Test
    public void updateMui_noExistingLocation() {
        assertTrue(model.insertUploader(uploader1.getName()));

        assertTrue(model.insertMui("Audio", uploader1, tags, size,
                availability, cost, sampRes1, sampRes2, uploadTime));

        assertFalse(model.updateMui(""));

        assertEquals(0,model.getMap().get(uploader1.getName()).getList().get(0).getAccessCount());
    }

    @Test
    public void getMaxCapacity_test() {
        assertEquals(1000, model.getMaxCapacity());
    }

    @Test
    public void getCapacity_currentCapacity() {
        assertTrue(model.insertUploader(uploader1.getName()));

        assertTrue(model.insertMui("Audio", uploader1, tags, size,
                availability, cost, sampRes1, sampRes2, uploadTime));

        assertTrue(model.insertMui("Video", uploader1, tags, size,
                availability, cost, sampRes1, sampRes2, uploadTime));

        assertTrue(model.insertMui("AudioVideo", uploader1, tags, size,
                availability, cost, sampRes1, sampRes2, uploadTime));

        assertEquals(300, model.getCurrentCapacity());
    }

}