package observerPattern;

import domainLogic.MediaUploadableAdmin;
import observerPatternContract.Observer;

import java.io.Serial;
import java.io.Serializable;

public class CapacityObserver implements Observer, Serializable {
    private MediaUploadableAdmin model;
    @Serial
    private static final long serialVersionUID = 1L;

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
