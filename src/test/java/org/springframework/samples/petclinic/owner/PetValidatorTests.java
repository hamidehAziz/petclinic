package org.springframework.samples.petclinic.owner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.samples.petclinic.mysql.domain.Pet;
import org.springframework.samples.petclinic.mysql.domain.PetType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import java.time.LocalDate;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@WebMvcTest(PetValidator.class)
public class PetValidatorTests {

    private PetValidator petValidator;

    @Mock
    private Pet pet1;
    @Mock
    private PetType cat = new PetType();


    @Before public void setUp() {
        petValidator = new PetValidator();
        pet1 = new Pet();
    }


    @Test
    public void testValidationWithEmptyFirstNamePet() throws Exception {
        pet1.setName("");
        pet1.setBirthDate(LocalDate.now());
        pet1.setType(cat);
        Errors errors = new BeanPropertyBindingResult(pet1, "invalidPet");

        petValidator.validate(pet1, errors);
        assertTrue(errors.hasErrors());
        assertNotNull(errors.getFieldError("name"));

    }

    @Test
    public void testValidationWithEmptyBirthDatePet() throws Exception {
        pet1.setName("Bonnie");
        pet1.setType(cat);
        Errors errors = new BeanPropertyBindingResult(pet1, "invalidPet");

        petValidator.validate(pet1, errors);
        assertTrue(errors.hasErrors());
        assertNotNull(errors.getFieldError("birthDate"));

    }

    @Test
    public void testValidationWithEmptyPetType() throws Exception {
        pet1.setName("Bonnie");
        pet1.setBirthDate(LocalDate.now());
        Errors errors = new BeanPropertyBindingResult(pet1, "invalidPet");

        petValidator.validate(pet1, errors);
        assertTrue(errors.hasErrors());
        assertNotNull(errors.getFieldError("type"));

    }
    }
