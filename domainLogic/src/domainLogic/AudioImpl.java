package domainLogic;

import contract.Audio;
import contract.Tag;
import contract.Uploader;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collection;

public class AudioImpl extends MediaUploadableItem implements Audio {
    private int samplingRate;

    public AudioImpl(Collection<Tag> tags, long size, Uploader uploader, Duration availability, BigDecimal cost, int samplingRate, LocalDateTime uploadTime, String mediaType) {
        super(tags, size, uploader, availability, cost, uploadTime, mediaType);
        this.samplingRate = samplingRate;
    }

    @Override
    public int getSamplingRate() {
        return samplingRate;
    }
}
