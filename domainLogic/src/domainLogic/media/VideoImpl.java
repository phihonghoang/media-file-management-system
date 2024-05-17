package domainLogic.media;

import contract.Tag;
import contract.Uploader;
import contract.Video;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.List;

public class VideoImpl extends MediaUploadableItem implements Video {
    private int resolution;

    public VideoImpl(String address, Collection<Tag> tags, long accessCount, long size, Uploader uploader, BigDecimal cost, int resolution) {
        super(address, tags, accessCount, size, uploader, cost);
        this.resolution = resolution;
    }

    @Override
    public int getResolution() {
        return resolution;
    }
}
