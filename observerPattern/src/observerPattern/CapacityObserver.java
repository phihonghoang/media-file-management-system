package observerPattern;

import domainLogic.MediaUploadableAdmin;
import observerPatternContract.Observer;

public class CapacityObserver implements Observer {
    private MediaUploadableAdmin model;

    public CapacityObserver(MediaUploadableAdmin model) {
        this.model = model;
    }

    @Override
    public void update() {

    }
}
