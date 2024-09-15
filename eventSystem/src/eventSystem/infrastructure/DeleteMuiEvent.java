package eventSystem.infrastructure;

import java.util.EventObject;

public class DeleteMuiEvent extends EventObject {
    private String location;

    public DeleteMuiEvent(Object source, String location) {
        super(source);
        this.location = location;
    }

    public String getLocation() {
        return location;
    }
}
