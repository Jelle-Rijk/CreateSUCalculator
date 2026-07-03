package org.jellerijk.mccreatecalc.application.publishers;

public interface Publisher<MessageType, EventIdentifier, InputType> {
    void subscribe(Subscription<MessageType, EventIdentifier> subscription);

    void publish(InputType message);
}
