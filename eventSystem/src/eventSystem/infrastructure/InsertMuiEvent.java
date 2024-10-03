package eventSystem.infrastructure;

import contract.Tag;
import contract.Uploader;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.EventObject;

public class InsertMuiEvent extends EventObject {
    private String mediaType;
    private Uploader uploader;
    private Collection<Tag> list;
    private long size;
    private Duration availability;
    private BigDecimal price;
    private int sampRes1;
    private int sampRes2;
    private LocalDateTime uploadTime;

    public InsertMuiEvent(Object source, String mediaType, Uploader uploader, Collection<Tag> list, long size, Duration availability, BigDecimal price, int sampRes1, int sampRes2, LocalDateTime uploadTime) {
        super(source);
        this.mediaType = mediaType;
        this.uploader = uploader;
        this.list = list;
        this.size = size;
        this.availability = availability;
        this.price = price;
        this.sampRes1 = sampRes1;
        this.sampRes2 = sampRes2;
        this.uploadTime = uploadTime;
    }

    public String getMediaType() {
        return mediaType;
    }

    public Uploader getUploader() {
        return uploader;
    }

    public Collection<Tag> getList() {
        return list;
    }

    public long getSize() {
        return size;
    }

    public Duration getAvailability() {
        return availability;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getSampRes1() {
        return sampRes1;
    }

    public int getSampRes2() {
        return sampRes2;
    }

    public LocalDateTime getUploadTime() {
        return uploadTime;
    }
}
