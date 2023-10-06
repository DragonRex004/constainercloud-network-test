package de.containercloud.event.impl;

import de.containercloud.event.Event;
import de.containercloud.event.EventData;

public class OnLoadConfig implements Event {
    @Override
    public String eventType() {
        return "OnLoadConfig";
    }

    @Override
    public void onTrigger(EventData data) {
        System.out.println("Config Event triggerd: " + EventData.deserialize(data, StringBuilder.class));
    }
}
