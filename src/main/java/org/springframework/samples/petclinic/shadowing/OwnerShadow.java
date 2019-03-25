package org.springframework.samples.petclinic.shadowing;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.mysql.domain.Owner;
import org.springframework.samples.petclinic.mysql.repo.MysqlOwnerRepository;
import org.springframework.samples.petclinic.postgres.domain.PostgresOwner;
import org.springframework.samples.petclinic.postgres.repo.PostgresOwnerRepository;
import org.springframework.stereotype.Service;

@Service
public class OwnerShadow {
    MysqlOwnerRepository mysqlOwnerRepository;
    PostgresOwnerRepository postgresOwnerRepository;

    @Autowired
    public OwnerShadow(MysqlOwnerRepository mysqlOwnerRepository, PostgresOwnerRepository postgresOwnerRepository){
        this.mysqlOwnerRepository = mysqlOwnerRepository;
        this.postgresOwnerRepository = postgresOwnerRepository;
    }

    public void shadowSave(Owner owner) {
        PostgresOwner postgresOwner = convertOwnerMysqlToPsg(owner);
        postgresOwnerRepository.save(postgresOwner);
        mysqlOwnerRepository.save(owner);
    }

    public Owner shadowReadById(int id) {
         PostgresOwner postgresOwner = postgresOwnerRepository.findById(id);
         return convertOwnerPsgToMysql(postgresOwner);
    }

    public Collection<Owner> shadowReadByLastName(String lastName) {
        Collection<PostgresOwner> postgresOwners = postgresOwnerRepository.findByLastName(lastName);
        Collection<Owner> owners = new ArrayList<>();
        for (PostgresOwner postgresOwner: postgresOwners){
            owners.add(convertOwnerPsgToMysql(postgresOwner));
        }
        return owners;
    }

    private PostgresOwner convertOwnerMysqlToPsg(Owner owner) {
        PostgresOwner postgresOwner = new PostgresOwner();
        postgresOwner.setAddress(owner.getAddress());
        postgresOwner.setCity(owner.getCity());
        postgresOwner.setTelephone(owner.getTelephone());
        postgresOwner.setFirstName(owner.getFirstName());
        postgresOwner.setLastName(owner.getLastName());
        return postgresOwner;
    }

    private Owner convertOwnerPsgToMysql(PostgresOwner postgresOwner){
        Owner owner = new Owner();
        owner.setAddress(postgresOwner.getAddress());
        owner.setCity(postgresOwner.getCity());
        owner.setTelephone(postgresOwner.getTelephone());
        owner.setFirstName(postgresOwner.getFirstName());
        owner.setLastName(postgresOwner.getLastName());
        return owner;
    }


}
