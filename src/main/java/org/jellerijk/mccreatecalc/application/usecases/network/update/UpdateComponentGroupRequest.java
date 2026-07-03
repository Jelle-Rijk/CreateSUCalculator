package org.jellerijk.mccreatecalc.application.usecases.network.update;

public record UpdateComponentGroupRequest(String groupId, Integer amount, Integer rpm, Integer sails) {

    public static final class Builder {
        private Integer amount;
        private String groupId;
        private Integer rpm;
        private Integer sails;

        private Builder() {
        }

        public static Builder aRequest() {
            return new Builder();
        }

        public UpdateComponentGroupRequest build() {
            return new UpdateComponentGroupRequest(groupId, amount, rpm, sails);
        }

        public Builder withAmount(Integer amount) {
            this.amount = amount;
            return this;
        }

        public Builder withGroupId(String groupId) {
            this.groupId = groupId;
            return this;
        }

        public Builder withRpm(Integer rpm) {
            this.rpm = rpm;
            return this;
        }

        public Builder withSails(Integer sails) {
            this.sails = sails;
            return this;
        }
    }
}
