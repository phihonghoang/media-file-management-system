package eventSystem.listener;

import domainLogic.MediaUploadableAdmin;
import eventSystem.infrastructure.EventListener;
import eventSystem.infrastructure.InsertUploaderEvent;

public class InsertUploaderListener implements EventListener<InsertUploaderEvent> {
    MediaUploadableAdmin model;

    public InsertUploaderListener(MediaUploadableAdmin model) {
        this.model = model;
    }

    @Override
    public void onEvent(InsertUploaderEvent event) {

    }
}
