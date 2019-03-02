package org.springframework.samples.petclinic.vet;

import java.util.Collection;

import org.springframework.stereotype.Service;

@Service
public class VetService {
    private VetRepository vetRepository;

    public VetService(VetRepository vetRepository){
        this.vetRepository = vetRepository;
    }


    public Collection<? extends Vet> findAll() {
        return vetRepository.findAll();
    }
}
