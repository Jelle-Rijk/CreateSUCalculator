package org.jellerijk.mccreatecalc.entities;

import org.jellerijk.mccreatecalc.entities.components.ComponentType;

public record ComponentGroup(ComponentType type, String name, String img, int amount, int sails, String level, int su, int rpm) {
}
