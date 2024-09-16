package eventSystem.infrastructure;

import java.util.EventObject;

public class DisplayContentEvent extends EventObject {
    private String mediaType;

    public DisplayContentEvent(Object source, String mediaType) {
        super(source);
        this.mediaType = mediaType;
    }

    public String getMediaType() {
        return mediaType;
    }
}
