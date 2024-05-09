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

    public AudioImpl() {
        this.accessCount = 0;
    }

    @Override
    public int getSamplingRate() {
        return 0;
    }

    @Override
    public String getAddress() {
        return "";
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
        return null;
    }

    @Override
    public Duration getAvailability() {
        return null;
    }

    @Override
    public BigDecimal getCost() {
        return null;
    }

    private void setAccessCount(long accessCount) {
        this.accessCount = accessCount;
    }
}
