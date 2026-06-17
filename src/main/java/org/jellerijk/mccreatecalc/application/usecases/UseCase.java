package org.jellerijk.mccreatecalc.application.usecases;

public interface UseCase<Request, Response> {
    /**
     * Executes the UseCase.
     *
     * @param request The org.jellerijk.mccreatecalc.data needed for the UseCase.
     */
    Response execute(Request request);

}
