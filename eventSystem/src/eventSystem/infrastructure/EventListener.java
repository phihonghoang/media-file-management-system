package eventSystem.infrastructure;

import java.util.EventObject;

public interface EventListener<T extends EventObject> {
    public void onEvent(T event);
}
