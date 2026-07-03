package org.jellerijk.mccreatecalc.application.usecases;

import org.jellerijk.mccreatecalc.application.gateways.ComponentGroupRepository;
import org.jellerijk.mccreatecalc.application.publishers.ComponentGroupPublisher;
import org.jellerijk.mccreatecalc.application.publishers.Observer;
import org.jellerijk.mccreatecalc.application.publishers.Subscription;
import org.jellerijk.mccreatecalc.application.services.ComponentGroupService;
import org.jellerijk.mccreatecalc.application.services.ComponentGroupServiceImpl;
import org.jellerijk.mccreatecalc.entities.ComponentGroup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ObserveComponentGroupUCTest {
    @Mock
    private Observer<ComponentGroup> observer;
    @Mock
    private ComponentGroupPublisher publisher;
    @Mock
    private ComponentGroup cg;
    @Mock
    private ComponentGroupRepository repo;
    private ObserveComponentGroupUC uc;
    private static final String VALID_ID = "test";

    @BeforeEach
    void setUp() {
        ComponentGroupService service = new ComponentGroupServiceImpl(repo, publisher);
        uc = new ObserveComponentGroupUC(service);
    }

    @Test
    void execute_IdExists_UpdatesObserver() {
        Subscription<ComponentGroup, String> subscription = new Subscription<>(observer, VALID_ID);
        when(repo.getById(VALID_ID)).thenReturn(Optional.of(cg));
        uc.execute(subscription);
        verify(observer).update(cg);
    }

    @Test
    void execute_IdDoesNotExist_ThrowsNSE() {
        Subscription<ComponentGroup, String> subscription = new Subscription<>(observer, "trust");
        when(repo.getById("trust")).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> uc.execute(subscription));
    }
}