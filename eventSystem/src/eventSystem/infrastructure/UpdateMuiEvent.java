package eventSystem.infrastructure;

import java.util.EventObject;

public class UpdateMuiEvent extends EventObject {
    private String location;

    public UpdateMuiEvent(Object source, String location) {
        super(source);
        this.location = location;
    }

    public String getLocation() {
        return location;
    }
}
