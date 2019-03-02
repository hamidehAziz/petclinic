package org.springframework.samples.petclinic.owner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;

public class OwnerServiceTest {


    @Mock
    private OwnerRepository ownerRepository;

    @Captor
    private ArgumentCaptor<Owner> ownerArgumentCaptor;

    private OwnerService ownerService;

    @Before
    public void setUp(){
        ownerService = new OwnerService(ownerRepository);
        initMocks(this);
    }

    @Test
    public void save() {
        Owner owner = anOwner();
        ownerService.save(owner);

        verify(ownerRepository).save(ownerArgumentCaptor.capture());
        final Owner captured = ownerArgumentCaptor.getValue();
        assertThat(captured).isNotNull();
        assertThat(captured.getId()).isEqualTo(1);
        assertThat(captured.getFirstName()).isEqualTo("alex");
    }

    @Test
    public void findById() {
        when(ownerRepository.findById(1)).thenReturn(Optional.of(anOwner()));
        ownerService.findById(1);
        verify(ownerRepository).findById(1);
        verifyNoMoreInteractions(ownerRepository);
    }

    private Owner anOwner(){
        Owner owner = new Owner();
        owner.setId(1);
        owner.setFirstName("alex");
        owner.setLastName("ali");
        return owner;
    }
}
