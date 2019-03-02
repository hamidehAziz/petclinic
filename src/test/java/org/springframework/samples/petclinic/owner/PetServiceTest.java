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

public class PetServiceTest {

    @Mock
    private PetRepository petRepository;

    @Captor
    private ArgumentCaptor<Pet> petArgumentCaptor;

    private PetService petService;

    @Before
    public void setUp(){
        petService = new PetService(petRepository);
        initMocks(this);
    }

    @Test
    public void save() {
        Pet pet = aPet();
        petService.save(pet);

        verify(petRepository).save(petArgumentCaptor.capture());
        final Pet captured = petArgumentCaptor.getValue();
        assertThat(captured).isNotNull();
        assertThat(captured.getId()).isEqualTo(1);
        assertThat(captured.getName()).isEqualTo("lucy");
    }

    @Test
    public void findById() {
        when(petRepository.findById(1)).thenReturn(Optional.of(aPet()));
        petService.findById(1);
        verify(petRepository).findById(1);
        verifyNoMoreInteractions(petRepository);
    }

    private Pet aPet(){
        Pet pet = new Pet();
        pet.setId(1);
        pet.setName("lucy");
        return pet;
    }
}
