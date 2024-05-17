package domainLogic.media;

import contract.Audio;
import contract.Tag;
import contract.Uploader;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.List;

public class AudioImpl extends MediaUploadableItem implements Audio {
    private int samplingRate;

    public AudioImpl(String address, Collection<Tag> tags, long accessCount, long size, Uploader uploader, BigDecimal cost, int samplingRate) {
        super(address, tags, accessCount, size, uploader, cost);
        this.samplingRate = samplingRate;
    }

    @Override
    public int getSamplingRate() {
        return samplingRate;
    }
}
