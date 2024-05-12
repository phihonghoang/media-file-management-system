package domainLogic.media;

import contract.Audio;
import contract.Tag;
import contract.Uploader;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.List;

public class AudioImpl implements Audio {
    private long accessCount;
    private Uploader uploader;
    private String address;

    public AudioImpl(Uploader uploader, String address) {
        this.accessCount = 0;
        this.uploader = uploader;
        this.address = address;
    }

    public AudioImpl() {
        this.accessCount = 0;
    }

    @Override
    public int getSamplingRate() {
        return 0;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public Collection<Tag> getTags() {
        return List.of();
    }

    @Override
    public long getAccessCount() {
        return this.accessCount;
    }

    @Override
    public long getSize() {
        return 0;
    }

    @Override
    public Uploader getUploader() {
        return uploader;
    }

    @Override
    public Duration getAvailability() {
        return null;
    }

    @Override
    public BigDecimal getCost() {
        return null;
    }

    public void setAccessCount(long accessCount) {
        this.accessCount = accessCount;
    }
}
