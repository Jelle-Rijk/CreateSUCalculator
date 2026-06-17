package org.jellerijk.mccreatecalc.application.usecases;

public interface UseCase<Request> {
    /**
     * Executes the UseCase.
     *
     * @param request The org.jellerijk.mccreatecalc.data needed for the UseCase.
     */
    void execute(Request request);

}
