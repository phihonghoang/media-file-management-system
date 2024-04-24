package domainLogic.media;

import contract.AudioVideo;
import contract.Tag;
import contract.Uploader;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.List;

public class AudioVideoImpl implements AudioVideo {
    @Override
    public int getSamplingRate() {
        return 0;
    }

    @Override
    public int getResolution() {
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
        return 0;
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
}
