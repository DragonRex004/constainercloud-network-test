package de.containercloud.event;

import java.util.ArrayList;
import java.util.List;

public class EventManager {
    private List<Event> events;

    public EventManager() {
        this.events = new ArrayList<>();
    }

    public void registerEvent(Event event) {
        this.events.add(event);
    }

    public void callEvent(String eventType, EventData eventData) {
        for (Event event : events) {
            if(event.eventType().equals(eventType)) {
                event.onTrigger(eventData);
            }
        }
    }
}
