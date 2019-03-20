package org.springframework.samples.petclinic.datatransfer.owner;

import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.owner.Owner;

import java.util.Collection;

public interface OwnerDAO {

    Collection<Owner> findByLastName(@Param("lastName") String lastName);

    Owner findById(@Param("id") Integer id);

    void save(Owner owner);

}
