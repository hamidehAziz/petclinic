package org.springframework.samples.petclinic.migration.builder;

import org.springframework.samples.petclinic.owner.Owner;

public class OwnerBuilder {

    public static Owner buildOwner(Integer id, String firstName, String lastName, String address, String city, String telephone) {
        Owner owner = new Owner();
        owner.setId(id);
        owner.setFirstName(firstName);
        owner.setLastName(lastName);
        owner.setAddress(address);
        owner.setCity(city);
        owner.setTelephone(telephone);

        return owner;
    }
}
