package eventSystem.listener;

import contract.Tag;
import domainLogic.MediaUploadableAdmin;
import eventSystem.infrastructure.DisplayTagEvent;
import eventSystem.infrastructure.EventListener;
import eventSystem.util.DisplayTagUtil;

import java.util.Set;

public class DisplayTagListener implements EventListener<DisplayTagEvent> {
    private MediaUploadableAdmin model;
    private DisplayTagUtil dtu;

    public DisplayTagListener(MediaUploadableAdmin model) {
        this.model = model;
        this.dtu = new DisplayTagUtil();
    }

    @Override
    public String onEvent(DisplayTagEvent event) {
        Set<Tag> currentTags = dtu.filterTagI(model.getMap().values());

        String result;
        if (event.getTagIE().equals("i")) {
            result = String.join(", ", currentTags.toString());
        } else {
            Set<Tag> unusedTags = dtu.filterTagE(currentTags);
            result = String.join(", ", unusedTags.toString());
        }

        return result;
    }
}
