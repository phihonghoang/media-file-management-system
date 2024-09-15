package eventSystem.listener;

import domainLogic.MediaUploadableAdmin;
import eventSystem.infrastructure.DeleteMuiEvent;
import eventSystem.infrastructure.EventListener;

public class DeleteMuiListener implements EventListener<DeleteMuiEvent> {
    MediaUploadableAdmin model;

    public DeleteMuiListener(MediaUploadableAdmin model) {
        this.model = model;
    }

    @Override
    public void onEvent(DeleteMuiEvent event) {
        this.model.deleteMui(event.getLocation());
    }
}
