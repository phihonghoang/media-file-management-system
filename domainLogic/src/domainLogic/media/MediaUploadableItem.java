package domainLogic.media;

import contract.MediaContent;
import contract.Tag;
import contract.Uploadable;
import contract.Uploader;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public abstract class MediaUploadableItem implements MediaContent, Uploadable, Serializable {
    private String address;
    private Collection<Tag> tags;
    private long accessCount;
    private long size;
    private Uploader uploader;
    private Duration availability;
    private BigDecimal cost;
    @Serial
    private static final long serialVersionUID = 1L;


    public MediaUploadableItem(Collection<Tag> tags, long size, Uploader uploader, BigDecimal cost) {
        this.address = UUID.randomUUID().toString();
        this.tags = tags;
        this.accessCount = 0;
        this.size = size;
        this.uploader = uploader;
        this.cost = cost;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public Collection<Tag> getTags() {
        return tags;
    }

    @Override
    public long getAccessCount() {
        return accessCount;
    }

    @Override
    public long getSize() {
        return size;
    }

    @Override
    public Uploader getUploader() {
        return uploader;
    }

    @Override
    public Duration getAvailability() {
        return availability;
    }

    @Override
    public BigDecimal getCost() {
        return cost;
    }

    public void increaseAccessCount() {
        accessCount++;
    }
}
