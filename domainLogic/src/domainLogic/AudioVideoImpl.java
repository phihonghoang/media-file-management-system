package domainLogic;

import contract.AudioVideo;
import contract.Tag;
import contract.Uploader;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collection;

public class AudioVideoImpl extends MediaUploadableItem implements AudioVideo {
    private int sampleRate;
    private int resolution;


    public AudioVideoImpl(Collection<Tag> tags, long size, Uploader uploader, Duration availability, BigDecimal cost, int sampleRate, int resolution, LocalDateTime uploadTime, String mediaType) {
        super(tags, size, uploader, availability, cost, uploadTime, mediaType);
        this.sampleRate = sampleRate;
        this.resolution = resolution;
    }

    @Override
    public int getSamplingRate() {
        return sampleRate;
    }

    @Override
    public int getResolution() {
        return resolution;
    }
}
