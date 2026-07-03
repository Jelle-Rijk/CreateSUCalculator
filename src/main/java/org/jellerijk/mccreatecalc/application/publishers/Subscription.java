package org.jellerijk.mccreatecalc.application.publishers;

public class Subscription<MessageType, EventIdentifier> {
    private final Observer<MessageType> observer;
    private final EventIdentifier eventIdentifier;

    public Subscription(Observer<MessageType> observer, EventIdentifier eventIdentifier) {
        this.observer = observer;
        this.eventIdentifier = eventIdentifier;
    }

    /**
     * @return The observer that acts as the subscriber.
     */
    public Observer<MessageType> getObserver() {
        return observer;
    }

    /**
     * @return The data that signals to the publisher when to notify the observer.
     */
    public EventIdentifier getEventIdentifier() {
        return eventIdentifier;
    }
}
