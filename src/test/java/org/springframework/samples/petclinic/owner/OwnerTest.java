package org.springframework.samples.petclinic.owner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OwnerTest {

    private Set<Pet> petsSet;

    private Owner owner;

    @Before
    public void setUp() {

        owner = new Owner();
        petsSet = new HashSet<Pet>();
        Pet pet1 = new Pet();
        pet1.setName("Jackie");
        Pet pet2 = new Pet();
        Pet pet3 = new Pet();
        petsSet.clear();
        petsSet.add(pet1);
        petsSet.add(pet2);
        petsSet.add(pet3);
    }

//    @Test
//    public void testGetPetWhenNameFound() {
//
//        Owner owner1 = Mockito.spy(owner);
//        String name = "Jackie";
//        when(owner1.getPetsInternal()).thenReturn(petsSet);
//
//        Pet result = owner1.getPet(name, false);
//
//        assertEquals(result.getName(), "Jackie");
//
//    }

    @Test
    public void testGetPetWhenNameNotFound() {

        Owner owner1 = Mockito.spy(owner);
        String name = "Jackie";
        when(owner1.getPetsInternal()).thenReturn(new HashSet<>());

        Pet result = owner1.getPet(name, false);

        assertEquals(result, null);

    }
}
