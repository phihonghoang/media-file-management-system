package eventSystem.listener;

import domainLogic.MediaUploadableAdmin;
import eventSystem.infrastructure.EventListener;
import eventSystem.infrastructure.SaveJosEvent;
import io.MediaUploadablePersistence;

public class SaveJosListener implements EventListener<SaveJosEvent> {
    private MediaUploadableAdmin model;
    private MediaUploadablePersistence persistence;

    public SaveJosListener(MediaUploadableAdmin model) {
        this.model = model;
        this.persistence = new MediaUploadablePersistence();
    }

    @Override
    public void onEvent(SaveJosEvent event) {
        String filename = "MediaUploadable.jos";
        persistence.save(filename, model);
    }
}
