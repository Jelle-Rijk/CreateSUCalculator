package org.jellerijk.mccreatecalc.presentation;

import org.jellerijk.mccreatecalc.application.usecases.network.CreateNetworkUseCase;
import org.jellerijk.mccreatecalc.application.usecases.network.FetchNetworksInfoUseCase;
import org.jellerijk.mccreatecalc.application.usecases.network.GetSelectedNetworkUseCase;
import org.jellerijk.mccreatecalc.application.usecases.network.SelectNetworkUseCase;

public interface NetworkUseCaseFactory {
//===== Public methods =====

    /**
     * @return Use case that can be used to create new stress networks.
     */
    CreateNetworkUseCase buildCreateNetworkUseCase();

    /**
     * @return Use case that can be used to fetch all network id's and names from storage.
     */
    FetchNetworksInfoUseCase buildFetchNetworksInfoUseCase();

    /**
     * @return Use case that can be used to fetch the selected stress network.
     */
    GetSelectedNetworkUseCase buildGetSelectedNetworkUseCase();

    /**
     * @return Use case that can be used to select a stress network.
     */
    SelectNetworkUseCase buildSelectNetworkUseCase();
}
