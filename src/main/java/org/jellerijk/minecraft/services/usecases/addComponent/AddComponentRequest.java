package org.jellerijk.minecraft.services.usecases.addComponent;

public interface AddComponentRequest {
    /**
     * @return The network id to add the component to.
     */
    String getNetworkId();

    /**
     * @return Whether the component is a generator. If this value is false, we assume the component to be a consumer.
     */
    boolean isGenerator();

    /**
     * @return Whether the component is a windmill.
     */
    boolean isWindmill();

    /**
     * @return The component's name.
     */
    String getComponentName();

    /**
     * @return The amount of components specified by {@link #getComponentName} to add.
     */
    int getAmount();

    /**
     * @return The number of windmill sails in this component.
     */
    int getSails();

    /**
     * @return The rpm this consumer operates at.
     */
    int getRpm();
}
