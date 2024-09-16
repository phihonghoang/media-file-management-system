package eventSystem.infrastructure;

import java.util.EventObject;

public class DisplayTagEvent extends EventObject {
    private String tagIE;

    public DisplayTagEvent(Object source, String tagIE) {
        super(source);
        this.tagIE = tagIE;
    }

    public String getTagIE() {
        return tagIE;
    }
}
