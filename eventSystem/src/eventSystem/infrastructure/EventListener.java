package eventSystem.infrastructure;

import java.util.EventObject;

public interface EventListener<T extends EventObject> {
    public String onEvent(T event);
}
