package eventSystem.listener;

import domainLogic.MediaUploadableAdmin;
import eventSystem.infrastructure.EventListener;
import eventSystem.infrastructure.UpdateMuiEvent;

public class UpdateMuiListener implements EventListener<UpdateMuiEvent> {
    MediaUploadableAdmin model;

    public UpdateMuiListener(MediaUploadableAdmin model) {
        this.model = model;
    }

    @Override
    public String onEvent(UpdateMuiEvent event) {
        this.model.updateMui(event.getLocation());

        return "";
    }
}
