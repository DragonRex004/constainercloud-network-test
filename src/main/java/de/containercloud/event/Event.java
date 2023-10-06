package de.containercloud.event;

public interface Event {

    String eventType();

    void onTrigger(EventData data);

}
