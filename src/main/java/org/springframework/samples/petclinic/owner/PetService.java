package org.springframework.samples.petclinic.owner;

import java.text.MessageFormat;
import java.util.Collection;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

@Service
public class PetService {

    private PetRepository petRepository;

    public PetService(PetRepository petRepository){
        this.petRepository = petRepository;
    }

    public void save(Pet pet) {
        petRepository.save(pet);
    }

    public Pet findById(int petId) {
        return petRepository.findById(petId).orElseThrow(()-> new EntityNotFoundException(MessageFormat.format("pet with id={0} not found", petId)));
    }

    public Collection<PetType> findPetTypes() {
        return petRepository.findPetTypes();
    }
}
