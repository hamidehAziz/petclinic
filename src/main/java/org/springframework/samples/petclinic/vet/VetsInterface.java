package org.springframework.samples.petclinic.vet;

import org.springframework.samples.petclinic.mysql.domain.Vet;

import java.util.List;

public interface VetsInterface {
    public List<Vet> getVetList();
}
