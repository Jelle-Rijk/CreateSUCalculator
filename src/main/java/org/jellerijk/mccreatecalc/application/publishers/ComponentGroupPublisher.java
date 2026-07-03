package org.jellerijk.mccreatecalc.application.publishers;

import org.jellerijk.mccreatecalc.entities.ComponentGroup;

import java.util.*;

public class ComponentGroupPublisher implements Publisher<ComponentGroup, String> {
    Map<String, List<Observer<ComponentGroup>>> subscriptions;

    public ComponentGroupPublisher() {
        this.subscriptions = new HashMap<>();
    }

    @Override
    public void subscribe(Subscription<ComponentGroup, String> subscription) {
        List<Observer<ComponentGroup>> observers = subscriptions.getOrDefault(subscription.getEventIdentifier(), new ArrayList<>());
        observers.add(subscription.getObserver());
        subscriptions.put(subscription.getEventIdentifier(), observers);
    }

    @Override
    public void publish(ComponentGroup message) {
        List<Observer<ComponentGroup>> observers = subscriptions.get(message.getId());
        if (observers == null)
            return;
        observers.forEach(obs -> obs.update(message));
    }
}
