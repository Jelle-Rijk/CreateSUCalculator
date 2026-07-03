package org.jellerijk.mccreatecalc.application.dto;

import org.jellerijk.mccreatecalc.entities.components.Component;
import org.jellerijk.mccreatecalc.entities.ComponentGroup;
import org.jellerijk.mccreatecalc.entities.components.Windmill;
import org.jellerijk.mccreatecalc.entities.components.ComponentType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public record ComponentGroupDTO(String id, ComponentType type, String name, String img, int amount, int sails,
                                String level, int su, int rpm) {
    public static ComponentGroupDTO map(ComponentGroup cg) {
        Component c = cg.getComponent();
        ComponentType type = cg.getComponentType();
        return new ComponentGroupDTO(cg.getId(), type, c.getName(), c.getImg(), cg.getAmount(), c instanceof Windmill w ? w.getSails() : 0, null, cg.calculateSu(), c.getRpm());
    }

    public static List<ComponentGroupDTO> map(List<ComponentGroup> cg) {
        return cg.stream().map(ComponentGroupDTO::map).collect(Collectors.toCollection(ArrayList::new));
    }

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

        public Builder(ComponentGroupDTO other) {
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

        public ComponentGroupDTO build() {
            return new ComponentGroupDTO(id, type, name, img, amount, sails, level, su, rpm);
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
