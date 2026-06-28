package org.jellerijk.mccreatecalc.entities.components;

import org.jellerijk.mccreatecalc.entities.Component;

public interface Consumer extends Component {
    int getSuConsumption();

    int getStressImpact();
}
