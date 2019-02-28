package org.springframework.samples.petclinic.owner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AnimalOwnerTest {

    @Mock
    private PetType petType;

    @Mock
    private Owner owner;

    private Pet pet;

    @Before
    public void setup() {
        pet = new Pet();
    }

    @Test
    public void AnimalOwnerGetSet(){

        pet.setType(petType);
        pet.setOwner(owner);

        pet.getType();
        pet.getOwner();

        when(owner.getCity()).thenReturn("Montreal");
        assertEquals("Montreal", pet.getOwner().getCity());

        when(petType.getName()).thenReturn("Mr. Pug");
        assertEquals("Mr. Pug", pet.getType().getName());

    }

}
