package org.springframework.samples.petclinic.model;

import org.junit.Test;
import org.junit.Before;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;

    @RunWith(MockitoJUnitRunner.class)
    public class PersonTest {

        private Person person;

    @Before
    public void setup() {
        person = new Person();
    }

    @Test
    public void testLastNameGetSet() {
        assertEquals(person.getLastName(), null);

        person.setLastName("Doe");
        assertEquals(person.getLastName(), "Doe");
    }

    @Test
    public void testFirstNameGetSet() {
        assertEquals(person.getFirstName(), null);

        person.setFirstName("John");
        assertEquals(person.getFirstName(), "John");
    } 
}
