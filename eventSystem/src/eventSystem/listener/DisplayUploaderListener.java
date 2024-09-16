package eventSystem.listener;

import domainLogic.MediaUploadableAdmin;
import eventSystem.infrastructure.DisplayUploaderEvent;
import eventSystem.infrastructure.EventListener;

public class DisplayUploaderListener implements EventListener<DisplayUploaderEvent> {
    MediaUploadableAdmin model;

    public DisplayUploaderListener(MediaUploadableAdmin model) {
        this.model = model;
    }

    @Override
    public void onEvent(DisplayUploaderEvent event) {
        if (model.getMap().keySet().isEmpty()) {
            System.out.println("Empty!");
            return;
        }

        for (String uploader: model.getMap().keySet()) {
            System.out.println("Produzenten: " + uploader + ", Mediadateien: " + model.getMap().get(uploader).getList().size());
        }
    }
}
