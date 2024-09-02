package observerPattern;

import domainLogic.MediaUploadableAdmin;
import observerPatternContract.Observer;

public class TagsObserver implements Observer {
    private MediaUploadableAdmin model;

    public TagsObserver(MediaUploadableAdmin model) {
        this.model = model;
    }

    @Override
    public void update() {

    }
}
