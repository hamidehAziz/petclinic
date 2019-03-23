package org.springframework.samples.petclinic.forklift;

import org.springframework.samples.petclinic.mysql.domain.Owner;
import org.springframework.samples.petclinic.mysql.domain.Pet;
import org.springframework.samples.petclinic.mysql.domain.Vet;
import org.springframework.samples.petclinic.mysql.domain.Visit;
import org.springframework.samples.petclinic.mysql.repo.MysqlOwnerRepository;
import org.springframework.samples.petclinic.mysql.repo.MysqlPetRepository;
import org.springframework.samples.petclinic.mysql.repo.MysqlVetRepository;
import org.springframework.samples.petclinic.mysql.repo.MysqlVisitRepository;
import org.springframework.samples.petclinic.postgres.domain.PostgresOwner;
import org.springframework.samples.petclinic.postgres.domain.PostgresPet;
import org.springframework.samples.petclinic.postgres.domain.PostgresVet;
import org.springframework.samples.petclinic.postgres.domain.PostgresVisit;
import org.springframework.samples.petclinic.postgres.repo.PostgresOwnerRepository;
import org.springframework.samples.petclinic.postgres.repo.PostgresPetRepository;
import org.springframework.samples.petclinic.postgres.repo.PostgresVetRepository;
import org.springframework.samples.petclinic.postgres.repo.PostgresVisitRepository;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class Forklifter {

    private MysqlOwnerRepository mysqlOwnerRepository;
    private MysqlPetRepository mysqlPetRepository;
    private MysqlVetRepository mysqlVetRepository;
    private MysqlVisitRepository mysqlVisitRepository;

    private PostgresOwnerRepository postgresOwnerRepository;
    private PostgresPetRepository postgresPetRepository;
    private PostgresVetRepository postgresVetRepository;
    private PostgresVisitRepository postgresVisitRepository;

    public Forklifter(MysqlOwnerRepository mysqlOwnerRepository, PostgresOwnerRepository postgresOwnerRepository, MysqlPetRepository mysqlPetRepository, PostgresPetRepository postgresPetRepository, MysqlVetRepository mysqlVetRepository, PostgresVetRepository postgresVetRepository, MysqlVisitRepository mysqlVisitRepository, PostgresVisitRepository postgresVisitRepository) {
        this.mysqlOwnerRepository = mysqlOwnerRepository;
        this.postgresOwnerRepository = postgresOwnerRepository;
        this.mysqlPetRepository = mysqlPetRepository;
        this.postgresPetRepository = postgresPetRepository;
        this.mysqlVetRepository = mysqlVetRepository;
        this.postgresVetRepository = postgresVetRepository;
        this.mysqlVisitRepository = mysqlVisitRepository;
        this.postgresVisitRepository = postgresVisitRepository;
    }

    public void forklift() {
        forkliftOwners();
        forkLiftPets();
        forkLiftVets();
        forkLiftVisits();
        //TODO forklift specialities
        //TODO forklift types
    }

    public void forkliftOwners() {
        List<Owner> owners = mysqlOwnerRepository.findAll();

        PostgresOwner postgresOwner;

        for (Owner owner : owners) {
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

    public void forkLiftPets() {
        List<Pet> pets = mysqlPetRepository.findPets();

        PostgresPet postgresPet;

        for (Pet pet : pets) {

            postgresPet = new PostgresPet();
            postgresPet.setBirthDate(pet.getBirthDate());
            postgresPet.setType(pet.getType());
            postgresPet.setOwner(pet.getOwner());
            postgresPetRepository.save(postgresPet);

        }

    }

    public void forkLiftVets() {
        Collection<Vet> vets = mysqlVetRepository.findAll();

        PostgresVet postgresVet;

        for (Vet vet : vets) {

            postgresVet = new PostgresVet();
            postgresVet.setSpecialtiesInternal(vet.getSpecialtiesInternal());
            postgresVetRepository.save(postgresVet);

        }

    }

    public void forkLiftVisits(){
        List<Visit> visits = mysqlVisitRepository.findAll();

        PostgresVisit postgresVisit;

        for (Visit visit : visits){

            postgresVisit = new PostgresVisit();
            postgresVisit.setDate(visit.getDate());
            postgresVisit.setDescription(visit.getDescription());
            postgresVisit.setPetId(visit.getPetId());
        }
    }

}
