package eventSystem.infrastructure;

import java.util.EventObject;
import java.util.LinkedList;
import java.util.List;

public class EventHandler<T extends EventObject> {
    private List<EventListener<T>> listenerList = new LinkedList<>();

    public void add(EventListener<T> listener) {
        listenerList.add(listener);
    }

    public void remove(EventListener<T> listener) {
        listenerList.remove(listener);
    }

    public String handle(T event) {
        for (EventListener<T> listener : listenerList) {
            String response = listener.onEvent(event);

            if (response != null) {
                return response;
            }
        }

        return "No response!";
    }

}
