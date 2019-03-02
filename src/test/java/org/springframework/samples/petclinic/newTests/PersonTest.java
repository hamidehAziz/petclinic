package org.springframework.samples.petclinic.newTests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.samples.petclinic.model.Person;
import org.springframework.util.SerializationUtils;

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

    @Test
    public void testSerialization() {
            Person test = new Person();
            test.setId(8456207);

            Person test2 = (Person) SerializationUtils.deserialize(SerializationUtils.serialize(test));
            assertThat(test2.getId()).isEqualTo(test.getId());
        }
    }
