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
    public void onEvent(DisplayContentEvent event) {
        List<MediaUploadableItem> filteredMediaType = dcu.filterMediaType(event.getMediaType(), model.getMap().values());

        if (filteredMediaType.isEmpty()) {
            System.out.println("Empty!");
            return;
        }

        for (MediaUploadableItem item : filteredMediaType) {
            System.out.println(event.getMediaType() + ", Abrufadresse: "  + item.getAddress() +  ", Verfuegbarkeit: " + item.getAvailability().plus(dcu.updateDuration(item.getUploadTime())).getSeconds() + " Sekunden, Abrufe: " + item.getAccessCount());
        }
    }
}
