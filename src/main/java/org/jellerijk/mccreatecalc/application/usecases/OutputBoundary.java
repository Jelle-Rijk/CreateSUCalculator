package org.jellerijk.mccreatecalc.application.usecases;

public interface OutputBoundary<Response> {
    void present(Response res);
}
