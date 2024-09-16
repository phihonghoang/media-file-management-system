package eventSystem.util;

import contract.Tag;
import domainLogic.MediaUploadableCRUD;
import domainLogic.MediaUploadableItem;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class DisplayTagUtil {

    public Set<Tag> filterTagI(Collection<MediaUploadableCRUD> mapValues) {
        Set<Tag> currentTags = new HashSet<>();

        for (MediaUploadableCRUD muCrud : mapValues) {
            for (MediaUploadableItem items : muCrud.getList()) {
                currentTags.addAll(items.getTags());
            }
        }

        return currentTags;
    }

    public Set<Tag> filterTagE(Collection<Tag> currentTags) {
        Set<Tag> unusedTags = new HashSet<>();

        for (Tag tag : Tag.values()) {
            if (!(currentTags.contains(tag))) {
                unusedTags.add(tag);
            }
        }

        return unusedTags;
    }
}
