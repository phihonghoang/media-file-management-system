package eventSystem.listener;

import domainLogic.MediaUploadableAdmin;
import eventSystem.infrastructure.EventListener;
import eventSystem.infrastructure.InsertMuiEvent;

public class InsertMuiListener implements EventListener<InsertMuiEvent> {
    MediaUploadableAdmin model;

    public InsertMuiListener(MediaUploadableAdmin model) {
        this.model = model;
    }

    @Override
    public void onEvent(InsertMuiEvent event) {
        this.model.insertMui(event.getMediaType(), event.getUploader(), event.getList(), event.getSize(), event.getAvailability(), event.getPrice(), event.getSampRes1(), event.getSampRes2());
    }
}
