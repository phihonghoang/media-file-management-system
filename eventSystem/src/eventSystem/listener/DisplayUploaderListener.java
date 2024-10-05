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
    public String onEvent(DisplayUploaderEvent event) {
        if (model.getMap().keySet().isEmpty()) {
            return "Empty!";
        }

        StringBuilder sb = new StringBuilder();
        for (String uploader: model.getMap().keySet()) {
            sb.append("Producer: ")
                    .append(uploader)
                    .append(", Media files: ")
                    .append(model.getMap().get(uploader).getList().size())
                    .append("\n");
        }

        sb.setLength(sb.length() - 1);

        return sb.toString();
    }
}
