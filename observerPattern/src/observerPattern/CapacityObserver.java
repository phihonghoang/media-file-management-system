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
        double percentageCapacity = ((double) model.getCurrentCapacity() / model.getMaxCapacity()) * 100;

        if (percentageCapacity > 90) {
            System.out.println("Capacity exceeds 90%");
        }
    }
}
