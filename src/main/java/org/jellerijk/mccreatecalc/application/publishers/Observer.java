package org.jellerijk.mccreatecalc.application.publishers;

public interface Observer<MessageType> {
    void update(MessageType message);
}
