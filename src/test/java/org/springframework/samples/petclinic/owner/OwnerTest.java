package org.springframework.samples.petclinic.owner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.samples.petclinic.mysql.domain.Owner;
import org.springframework.samples.petclinic.mysql.domain.Pet;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OwnerTest {

    @Mock
    private Set<Pet> pets;

    private Owner owner;

    @Before
    public void setUp() {

        owner = new Owner();
        pets = new HashSet<Pet>();
        Pet pet1 = new Pet();
        pet1.setName("Jackie");
        Pet pet2 = new Pet();
        Pet pet3 = new Pet();

        pets.add(pet1);
        pets.add(pet2);
        pets.add(pet3);
    }

    @Test
    public void testGetPetWhenNameFound() {

        Owner owner1 = Mockito.spy(owner);
        String name = "Jackie";
        when(owner1.getPetsInternal()).thenReturn(pets);

        Pet result = owner1.getPet(name, false);

        assertEquals(result.getName(), "Jackie");

    }

    @Test
    public void testGetPetWhenNameNotFound() {

        Owner owner1 = Mockito.spy(owner);
        String name = "Jackie";
        when(owner1.getPetsInternal()).thenReturn(new HashSet<>());

        Pet result = owner1.getPet(name, false);

        assertEquals(result, null);

    }
}
