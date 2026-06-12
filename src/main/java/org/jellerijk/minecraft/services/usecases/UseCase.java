package org.jellerijk.minecraft.services.usecases;

public interface UseCase<Request, Response> {
    Response execute(Request request);

}
