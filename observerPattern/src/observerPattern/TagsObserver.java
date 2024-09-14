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
    private Set<Tag> tempTags;

    public TagsObserver(MediaUploadableAdmin model) {
        this.model = model;
        previousTags = new HashSet<>();
        tempTags = new HashSet<>();
    }

    @Override
    public void update() {
        Set<Tag> currentTags = addTags(model.getMap().values());
        String result = "";
        String ar = "";

        for (Tag tag : currentTags) {
            if (!previousTags.contains(tag)) {
                tempTags.add(tag);
                ar = "Tag added: ";
            }
        }

        for (Tag tag : previousTags) {
            if (!currentTags.contains(tag)) {
                tempTags.add(tag);
                ar = "Tag removed: ";
            }
        }

        result = String.join(", ", tempTags.toString());

        if (!result.equals("[]")) {
            System.out.println(ar + result);
        }

        previousTags = currentTags;
        tempTags.clear();
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
