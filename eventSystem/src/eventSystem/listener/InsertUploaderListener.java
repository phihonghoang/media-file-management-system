package eventSystem.listener;

import domainLogic.MediaUploadableAdmin;
import eventSystem.infrastructure.EventListener;
import eventSystem.infrastructure.InsertUploaderEvent;

public class InsertUploaderListener implements EventListener<InsertUploaderEvent> {
    private MediaUploadableAdmin model;

    public InsertUploaderListener(MediaUploadableAdmin model) {
        this.model = model;
    }

    @Override
    public String onEvent(InsertUploaderEvent event) {
        this.model.insertUploader(event.getUploader());

        return "";
    }
}
