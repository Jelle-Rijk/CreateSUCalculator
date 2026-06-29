package org.jellerijk.mccreatecalc.data.repositories;

import org.jellerijk.mccreatecalc.application.gateways.ComponentRepository;
import org.jellerijk.mccreatecalc.data.dao.ConsumerDAO;
import org.jellerijk.mccreatecalc.entities.components.*;

import java.util.List;

public class ComponentRepositoryImpl implements ComponentRepository {

    private final ConsumerDAO consumerDAO;

    public ComponentRepositoryImpl(ConsumerDAO consumerDAO) {
        this.consumerDAO = consumerDAO;
    }

    @Override
    public List<Consumer> getAllConsumers() {
        return consumerDAO.getAll();
    }

    @Override
    public List<Generator> getAllGenerators() {
        WaterWheel small = new WaterWheel(WaterWheelType.SMALL);
        WaterWheel large = new WaterWheel(WaterWheelType.LARGE);
        Windmill windmill = new WindmillImpl(0);
        return List.of(small, large, windmill);
    }
}
