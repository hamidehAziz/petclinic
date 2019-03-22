package org.springframework.samples.petclinic.forklift;

import org.springframework.samples.petclinic.mysql.domain.Owner;
import org.springframework.samples.petclinic.mysql.repo.MysqlOwnerRepository;
import org.springframework.samples.petclinic.postgres.domain.PostgresOwner;
import org.springframework.samples.petclinic.postgres.repo.PostgresOwnerRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Forklifter {

    private MysqlOwnerRepository mysqlOwnerRepository;

    private PostgresOwnerRepository postgresOwnerRepository;

    public Forklifter(MysqlOwnerRepository mysqlOwnerRepository, PostgresOwnerRepository postgresOwnerRepository) {
        this.mysqlOwnerRepository = mysqlOwnerRepository;
        this.postgresOwnerRepository = postgresOwnerRepository;
    }

    public void forklift() {
        forliftOwners();
        //TODO forlift pets
        //TODO forlift specialities
        //TODO forlift types
        //TODO forlift vets
        //TODO forlift visits
    }

    public void forliftOwners() {
        List<Owner> owners = mysqlOwnerRepository.findAll();

        PostgresOwner postgresOwner;

        for (Owner owner: owners) {
            postgresOwner = new PostgresOwner();
            postgresOwner.setId(owner.getId());
            postgresOwner.setAddress(owner.getAddress());
            postgresOwner.setCity(owner.getCity());
            postgresOwner.setTelephone(owner.getTelephone());
            postgresOwner.setFirstName(owner.getFirstName());
            postgresOwner.setLastName(owner.getLastName());
            postgresOwnerRepository.save(postgresOwner);
        }
    }
}
