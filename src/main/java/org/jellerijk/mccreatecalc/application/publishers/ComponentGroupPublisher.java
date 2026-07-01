package org.jellerijk.mccreatecalc.application.publishers;

import org.jellerijk.mccreatecalc.entities.ComponentGroup;

import java.util.*;

public class ComponentGroupPublisher implements Publisher<ComponentGroup, String> {
    Map<String, List<Observer<ComponentGroup>>> subscriptions;

    public ComponentGroupPublisher() {
        this.subscriptions = new HashMap<>();
    }

    /**
     * @param observer The observer to subscribe.
     * @param groupId  The groupId of the component group to subscribe to.
     */
    @Override
    public void subscribe(Observer<ComponentGroup> observer, String groupId) {
        List<Observer<ComponentGroup>> observers = subscriptions.getOrDefault(groupId, new ArrayList<>());
        observers.add(observer);
        subscriptions.put(groupId, observers);
    }

    @Override
    public void publish(ComponentGroup message) {
        List<Observer<ComponentGroup>> observers = subscriptions.get(message.getId());
        if (observers == null)
            return;
        observers.forEach(obs -> obs.update(message));
    }
}
