package org.jellerijk.mccreatecalc.application.publishers;

public interface Publisher<MessageType, EventIdentifier> {
    void subscribe(Subscription<MessageType, EventIdentifier> subscription);

    void publish(MessageType message);
}
