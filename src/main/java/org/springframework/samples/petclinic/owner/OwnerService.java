package org.springframework.samples.petclinic.owner;

import java.text.MessageFormat;
import java.util.Collection;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

@Service
public class OwnerService {

    private OwnerRepository ownerRepository;

    public OwnerService(OwnerRepository ownerRepository){
        this.ownerRepository = ownerRepository;
    }

    public void save(Owner owner) {
        ownerRepository.save(owner);
    }

    public Owner findById(int ownerId) throws EntityNotFoundException {
        return ownerRepository.findById(ownerId).orElseThrow(() -> new EntityNotFoundException(MessageFormat.format("owner with id={0} not found",ownerId)));
    }

    public Collection<Owner> findByLastName(String lastName) {
        return ownerRepository.findByLastName(lastName);
    }
}
