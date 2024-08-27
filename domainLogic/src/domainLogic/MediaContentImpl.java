package domainLogic;

import contract.MediaContent;
import contract.Tag;

import java.util.Collection;
import java.util.List;

public class MediaContentImpl implements MediaContent {
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
}
