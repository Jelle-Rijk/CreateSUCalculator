package org.jellerijk.mccreatecalc.entities;

import org.jellerijk.mccreatecalc.entities.components.ComponentType;

public record ComponentGroup(String id, ComponentType type, String name, String img, int amount, int sails,
                             String level, int su, int rpm) {
    public static final class Builder {
        private int amount;
        private String id;
        private String img;
        private String level;
        private String name;
        private int rpm;
        private int sails;
        private int su;
        private ComponentType type;

        public Builder() {
        }

        public Builder(ComponentGroup other) {
            this.amount = other.amount();
            this.id = other.id();
            this.img = other.img();
            this.level = other.level();
            this.name = other.name();
            this.rpm = other.rpm();
            this.sails = other.sails();
            this.su = other.su();
            this.type = other.type();
        }

        public static Builder aComponentGroup() {
            return new Builder();
        }

        public ComponentGroup build() {
            return new ComponentGroup(id, type, name, img, amount, sails, level, su, rpm);
        }

        public Builder withAmount(int amount) {
            this.amount = amount;
            return this;
        }

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withImg(String img) {
            this.img = img;
            return this;
        }

        public Builder withLevel(String level) {
            this.level = level;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withRpm(int rpm) {
            this.rpm = rpm;
            return this;
        }

        public Builder withSails(int sails) {
            this.sails = sails;
            return this;
        }

        public Builder withSu(int su) {
            this.su = su;
            return this;
        }

        public Builder withType(ComponentType type) {
            this.type = type;
            return this;
        }
    }
}
