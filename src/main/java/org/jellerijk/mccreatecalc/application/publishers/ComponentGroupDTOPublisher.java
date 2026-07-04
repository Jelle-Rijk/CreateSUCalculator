package org.jellerijk.mccreatecalc.application.publishers;

import org.jellerijk.mccreatecalc.application.dto.ComponentGroupDTO;
import org.jellerijk.mccreatecalc.entities.ComponentGroup;

import java.util.*;

public class ComponentGroupDTOPublisher implements Publisher<ComponentGroupDTO, String, ComponentGroup> {
    Map<String, List<Observer<ComponentGroupDTO>>> subscriptions;

    public ComponentGroupDTOPublisher() {
        this.subscriptions = new HashMap<>();
    }

    @Override
    public void subscribe(Subscription<ComponentGroupDTO, String> subscription) {
        subscriptions.computeIfAbsent(subscription.getEventIdentifier(), _ -> new ArrayList<>())
                .add(subscription.getObserver());
    }

    @Override
    public void publish(ComponentGroup message) {
        List<Observer<ComponentGroupDTO>> observers = subscriptions.get(message.getId());
        if (observers == null)
            return;
        observers.forEach(obs -> obs.update(ComponentGroupDTO.map(message)));
    }

    public void unsubscribe(Subscription<ComponentGroupDTO, String> subscription) {
        subscriptions.compute(subscription.getEventIdentifier(), (_, value) -> {
            if (value == null)
                return null;
            value.remove(subscription.getObserver());
            return value.isEmpty() ? null : value;
        });
    }

    boolean containsKey(String key) {
        return subscriptions.containsKey(key);
    }
}
