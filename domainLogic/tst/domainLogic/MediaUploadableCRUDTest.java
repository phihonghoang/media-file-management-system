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
import static org.mockito.Mockito.*;

class MediaUploadableCRUDTest {
    private MediaUploadableItem audio;
    private MediaUploadableItem video;
    private MediaUploadableItem audioVideo;
    private MediaUploadableCRUD crud;


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

        crud = new MediaUploadableCRUD();
    }

    @Test
    public void insert_success() {
        boolean audioRes = crud.insert(audio);
        boolean videoRes = crud.insert(video);
        boolean audioVideoRes = crud.insert(audioVideo);

        assertTrue(audioRes);
        assertTrue(videoRes);
        assertTrue(audioVideoRes);
    }

    @Test
    public void insert_null() {
        boolean res = crud.insert(null);

        assertFalse(res);
    }

    @Test
    public void delete_success() {
        crud.insert(audio);
        crud.insert(video);
        crud.insert(audioVideo);

        MediaUploadableItem audioDel = crud.delete(audio.getAddress());
        MediaUploadableItem videoDel = crud.delete(video.getAddress());
        MediaUploadableItem audioVideoDel = crud.delete(audioVideo.getAddress());

        assertEquals(audio, audioDel);
        assertEquals(video, videoDel);
        assertEquals(audioVideo, audioVideoDel);
    }

    @Test
    public void delete_nonExistingLocation() {
        MediaUploadableItem mui = crud.delete("1234");

        assertNull(mui);
    }

    @Test
    public void delete_null() {
        MediaUploadableItem mui = crud.delete(null);

        assertNull(mui);
    }

    @Test
    public void getList_exist() {
        List<MediaUploadableItem> muiList = crud.getList();

        assertEquals(0, muiList.size());
    }

    @Test
    public void getList_insert() {
        crud.insert(audio);
        crud.insert(video);
        crud.insert(audioVideo);

        List<MediaUploadableItem> muiList = crud.getList();

        assertEquals(3, muiList.size());
    }

    @Test
    public void update_success() {
        crud.insert(audio);
        crud.insert(video);
        crud.insert(audioVideo);

        boolean audioRes = crud.update(audio.getAddress());
        boolean videoRes = crud.update(video.getAddress());
        boolean audioVideoRes = crud.update(audioVideo.getAddress());

        assertTrue(audioRes);
        assertTrue(videoRes);
        assertTrue(audioVideoRes);
    }

    @Test
    public void update_accessAccount_success() {
        crud.insert(audio);
        crud.insert(video);
        crud.insert(audioVideo);

        crud.update(audio.getAddress());
        crud.update(video.getAddress());
        crud.update(audioVideo.getAddress());

        assertEquals(1, audio.getAccessCount());
        assertEquals(1, video.getAccessCount());
        assertEquals(1, audioVideo.getAccessCount());
    }

    @Test
    public void update_null() {
        boolean res = crud.update(null);

        assertFalse(res);
    }

    @Test
    public void update_nonExistingLocation() {
        boolean res = crud.update("1234");

        assertFalse(res);
    }

    @Test
    public void insert_success_mock() {
        MediaUploadableCRUD crud = new MediaUploadableCRUD();
        MediaUploadableItem item = mock(MediaUploadableItem.class);

        assertTrue(crud.insert(item));
        assertEquals(1, crud.getList().size());
    }

    @Test
    public void insert_success_more_mock() {
        MediaUploadableCRUD crud = new MediaUploadableCRUD();
        MediaUploadableItem item = mock(MediaUploadableItem.class);

        assertTrue(crud.insert(item));
        assertTrue(crud.insert(item));
        assertEquals(2, crud.getList().size());
    }

    @Test
    public void delete_success_mock() {
        MediaUploadableCRUD crud = new MediaUploadableCRUD();
        MediaUploadableItem item = mock(MediaUploadableItem.class);

        when(item.getAddress()).thenReturn("1");
        assertTrue(crud.insert(item));

        assertNotNull(crud.delete(item.getAddress()));
        assertEquals(0, crud.getList().size());
    }

    @Test
    public void update_success_mock() {
        MediaUploadableCRUD crud = new MediaUploadableCRUD();
        MediaUploadableItem item = mock(MediaUploadableItem.class);

        when(item.getAddress()).thenReturn("1");
        assertTrue(crud.insert(item));

        assertTrue(crud.update(item.getAddress()));
        verify(item, times(1)).increaseAccessCount();
    }

    @Test
    public void update_success_more_mock() {
        MediaUploadableCRUD crud = new MediaUploadableCRUD();
        MediaUploadableItem item = mock(MediaUploadableItem.class);

        when(item.getAddress()).thenReturn("1");
        assertTrue(crud.insert(item));

        assertTrue(crud.update(item.getAddress()));
        assertTrue(crud.update(item.getAddress()));
        verify(item, times(2)).increaseAccessCount();
    }

}