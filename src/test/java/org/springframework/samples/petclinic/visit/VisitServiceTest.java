package org.springframework.samples.petclinic.visit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;

public class VisitServiceTest {

    @Mock
    private VisitRepository visitRepository;

    @Captor
    private ArgumentCaptor<Visit> visitArgumentCaptor;

    private VisitService visitService;

    @Before
    public void setUp(){
        visitService = new VisitService(visitRepository);
        initMocks(this);
    }

    @Test
    public void save() {
        Visit visit = aVisit();
        visitService.save(visit);

        verify(visitRepository).save(visitArgumentCaptor.capture());
        final Visit captured = visitArgumentCaptor.getValue();
        assertThat(captured).isNotNull();
        assertThat(captured.getPetId()).isEqualTo(3);
        assertThat(captured.getId()).isEqualTo(1);
    }

    private Visit aVisit(){
        Visit visit = new Visit();
        visit.setId(1);
        visit.setPetId(3);
        return visit;
    }
}
