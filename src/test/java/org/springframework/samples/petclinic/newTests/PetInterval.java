package org.springframework.samples.petclinic.newTests;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.samples.petclinic.owner.Owner;
import org.springframework.samples.petclinic.owner.Pet;

@RunWith(MockitoJUnitRunner.class)
public class PetInterval {

    private Owner petOwner;

    @Before
    public void setup() {
        petOwner = new Owner();
    }

    @Test
    public void testIntervalGetSet() {

        Pet mockPug = mock(Pet.class);
        Pet mockSnowy = mock(Pet.class);
        Set<Pet> setPet = new HashSet< >();

        assertEquals(petOwner.getPetsInternal(), setPet);

        setPet.add(mockPug);
        setPet.add(mockSnowy);
        petOwner.setPetsInternal(setPet);
        assertEquals(petOwner.getPetsInternal(), setPet);

    }

}
