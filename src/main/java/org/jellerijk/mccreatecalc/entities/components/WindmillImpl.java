package org.jellerijk.mccreatecalc.entities.components;

import org.jellerijk.mccreatecalc.entities.Windmill;

public class WindmillImpl extends BaseComponent implements Windmill {
    private static final String IMAGE = "create_white_sail.png";
    private static final int MAX_RPM = 16;
    private static final int MAX_SAILS = 128; // Point at which max rpm is reached.
    private static final String NAME = "Windmill";
    private static final int SU_PER_RPM = 512;
    private final int sails;

    public WindmillImpl(int sails) {
        super(NAME, IMAGE);
        if (sails < 0)
            throw new IllegalArgumentException("A windmill cannot have a negative number of sails.");
        this.sails = sails;
    }

//===== Public methods =====
    @Override
    public int getSuProduction() {
        return getRpm() * SU_PER_RPM;
    }

@Override
    public int getRpm() {
        if (sails >= MAX_SAILS)
            return MAX_RPM;
        return sails / 8;
    }

    @Override
    public int getSails() {
        return sails;
    }

    public static final class Builder {
        private int sails;

        private Builder() {
        }

//===== Static methods =====
        public static Builder aWindmillImpl() {
            return new Builder();
        }

//===== Public methods =====
        public WindmillImpl build() {
            return new WindmillImpl(sails);
        }

        public Builder withSails(int sails) {
            this.sails = sails;
            return this;
        }
    }
}
