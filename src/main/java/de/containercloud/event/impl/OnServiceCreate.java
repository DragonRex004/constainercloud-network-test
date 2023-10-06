package de.containercloud.event.impl;

import de.containercloud.event.Event;
import de.containercloud.event.EventData;

public class OnServiceCreate implements Event {
    @Override
    public String eventType() {
        return "OnServiceCreate";
    }

    @Override
    public void onTrigger(EventData data) {
        System.out.println("Service Create Event is triggered: " + EventData.deserialize(data, StringBuilder.class));
    }
}
