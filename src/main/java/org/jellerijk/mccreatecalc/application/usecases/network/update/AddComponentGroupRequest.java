package org.jellerijk.mccreatecalc.application.usecases.network.update;

import org.jellerijk.mccreatecalc.entities.components.ComponentType;
import org.jellerijk.mccreatecalc.entities.components.WaterWheelType;

public record AddComponentGroupRequest(ComponentType type, WaterWheelType waterWheelType, String name) {

    public static final class Builder {
        private String name;
        private ComponentType type;
        private WaterWheelType waterWheelType;

        private Builder() {
        }

        public static Builder anAddComponentGroupRequest() {
            return new Builder();
        }

        public AddComponentGroupRequest build() {
            return new AddComponentGroupRequest(type, waterWheelType, name);
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withType(ComponentType type) {
            this.type = type;
            return this;
        }

        public Builder withWaterWheelType(WaterWheelType waterWheelType) {
            this.waterWheelType = waterWheelType;
            return this;
        }
    }
}
