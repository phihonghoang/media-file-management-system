package observerPattern;

import contract.Tag;
import domainLogic.MediaUploadableAdmin;
import domainLogic.MediaUploadableCRUD;
import domainLogic.MediaUploadableItem;
import observerPatternContract.Observer;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class TagsObserver implements Observer {
    private MediaUploadableAdmin model;
    private Set<Tag> previousTags;

    public TagsObserver(MediaUploadableAdmin model) {
        this.model = model;
        previousTags = new HashSet<>();
    }

    @Override
    public void update() {
        Set<Tag> currentTags = addTags(model.getMap().values());

        for (Tag tag : currentTags) {
            if (!previousTags.contains(tag)) {
                System.out.println("Tag added: " + "[" + tag + "]");
            }
        }

        for (Tag tag : previousTags) {
            if (!currentTags.contains(tag)) {
                System.out.println("Tag removed: " + "[" + tag + "]");
            }
        }

        previousTags = currentTags;
    }

    public Set<Tag> addTags(Collection<MediaUploadableCRUD> mapValues) {
        Set<Tag> tags = new HashSet<>();

        for (MediaUploadableCRUD muCrud : mapValues) {
            for (MediaUploadableItem items : muCrud.getList()) {
                tags.addAll(items.getTags());
            }
        }

        return tags;
    }
}
