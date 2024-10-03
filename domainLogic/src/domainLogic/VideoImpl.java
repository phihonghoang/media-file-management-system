package domainLogic;

import contract.Tag;
import contract.Uploader;
import contract.Video;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collection;

public class VideoImpl extends MediaUploadableItem implements Video {
    private int resolution;

    public VideoImpl(Collection<Tag> tags, long size, Uploader uploader, Duration availability, BigDecimal cost, int resolution, LocalDateTime uploadTime, String mediaType) {
        super(tags, size, uploader, availability, cost, uploadTime, mediaType);
        this.resolution = resolution;
    }

    @Override
    public int getResolution() {
        return resolution;
    }
}
