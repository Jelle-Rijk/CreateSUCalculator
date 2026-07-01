package org.jellerijk.mccreatecalc.application.publishers;

public interface Publisher<MessageType, EventIdentifier> {
    void subscribe(Observer<MessageType> observer, EventIdentifier event);

    void publish(MessageType message);
}
