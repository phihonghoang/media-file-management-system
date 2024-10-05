package eventSystem.listener;

import domainLogic.MediaUploadableAdmin;
import domainLogic.MediaUploadableItem;
import eventSystem.infrastructure.DisplayContentEvent;
import eventSystem.infrastructure.EventListener;
import eventSystem.util.DisplayContentUtil;

import java.util.List;

public class DisplayContentListener implements EventListener<DisplayContentEvent> {
    private MediaUploadableAdmin model;
    private DisplayContentUtil dcu;

    public DisplayContentListener(MediaUploadableAdmin model) {
        this.model = model;
        this.dcu = new DisplayContentUtil();
    }

    @Override
    public String onEvent(DisplayContentEvent event) {
        List<MediaUploadableItem> filteredMediaType = dcu.filterMediaType(event.getMediaType(), model.getMap().values());

        if (filteredMediaType.isEmpty()) {
            return "Empty!";
        }

        StringBuilder sb = new StringBuilder();
        for (MediaUploadableItem item : filteredMediaType) {
            sb.append(item.getMediaType())
                    .append(", Address: ")
                    .append(item.getAddress())
                    .append(", Availability: ")
                    .append(dcu.updateDuration(item.getUploadTime()).toMinutes())
                    .append(" Minutes, Updates: ")
                    .append(item.getAccessCount())
                    .append("\n");
        }

        sb.setLength(sb.length() - 1);

        return sb.toString();
    }
}
