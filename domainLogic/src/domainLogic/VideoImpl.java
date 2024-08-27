package domainLogic;

import contract.Tag;
import contract.Uploader;
import contract.Video;

import java.math.BigDecimal;
import java.util.Collection;

public class VideoImpl extends MediaUploadableItem implements Video {
    private int resolution;

    public VideoImpl(Collection<Tag> tags, long size, Uploader uploader, BigDecimal cost, int resolution) {
        super(tags, size, uploader, cost);
        this.resolution = resolution;
    }

    @Override
    public int getResolution() {
        return resolution;
    }
}
