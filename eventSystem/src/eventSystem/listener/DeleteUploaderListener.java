package eventSystem.listener;

import domainLogic.MediaUploadableAdmin;
import eventSystem.infrastructure.DeleteUploaderEvent;
import eventSystem.infrastructure.EventListener;

public class DeleteUploaderListener implements EventListener<DeleteUploaderEvent> {
    private MediaUploadableAdmin model;

    public DeleteUploaderListener(MediaUploadableAdmin model) {
        this.model = model;
    }

    @Override
    public String onEvent(DeleteUploaderEvent event) {
        this.model.deleteUploader(event.getUploader());

        return "";
    }
}
