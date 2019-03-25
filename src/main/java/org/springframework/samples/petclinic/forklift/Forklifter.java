package org.springframework.samples.petclinic.forklift;

import java.util.Collection;
import java.util.List;

import org.springframework.samples.petclinic.mysql.domain.Owner;
import org.springframework.samples.petclinic.mysql.domain.Pet;
import org.springframework.samples.petclinic.mysql.domain.Specialty;
import org.springframework.samples.petclinic.mysql.domain.Vet;
import org.springframework.samples.petclinic.mysql.domain.Visit;
import org.springframework.samples.petclinic.mysql.repo.MysqlOwnerRepository;
import org.springframework.samples.petclinic.mysql.repo.MysqlPetRepository;
import org.springframework.samples.petclinic.mysql.repo.MysqlSpecialtyRepository;
import org.springframework.samples.petclinic.mysql.repo.MysqlVetRepository;
import org.springframework.samples.petclinic.mysql.repo.MysqlVisitRepository;
import org.springframework.samples.petclinic.postgres.domain.PostgresOwner;
import org.springframework.samples.petclinic.postgres.domain.PostgresPet;
import org.springframework.samples.petclinic.postgres.domain.PostgresPetType;
import org.springframework.samples.petclinic.postgres.domain.PostgresSpecialty;
import org.springframework.samples.petclinic.postgres.domain.PostgresVet;
import org.springframework.samples.petclinic.postgres.domain.PostgresVisit;
import org.springframework.samples.petclinic.postgres.repo.PostgresOwnerRepository;
import org.springframework.samples.petclinic.postgres.repo.PostgresPetRepository;
import org.springframework.samples.petclinic.postgres.repo.PostgresSpecialtyRepository;
import org.springframework.samples.petclinic.postgres.repo.PostgresVetRepository;
import org.springframework.samples.petclinic.postgres.repo.PostgresVisitRepository;
import org.springframework.stereotype.Component;

@Component
public class Forklifter {

    private MysqlOwnerRepository mysqlOwnerRepository;
    private MysqlPetRepository mysqlPetRepository;
    private MysqlVetRepository mysqlVetRepository;
    private MysqlSpecialtyRepository mysqlSpecialtyRepository;
    private MysqlVisitRepository mysqlVisitRepository;
    //private MysqlPetTypesRepository mysqlPetTypesRepository;


    private PostgresOwnerRepository postgresOwnerRepository;
    private PostgresPetRepository postgresPetRepository;
    private PostgresVetRepository postgresVetRepository;
    private PostgresSpecialtyRepository postgresSpecialtyRepository;
    private PostgresVisitRepository postgresVisitRepository;
    //private PostgresPetTypesRepository postgresPetTypesRepository;


    public Forklifter(MysqlOwnerRepository mysqlOwnerRepository, PostgresOwnerRepository postgresOwnerRepository, MysqlPetRepository mysqlPetRepository, PostgresPetRepository postgresPetRepository, MysqlVetRepository mysqlVetRepository, PostgresVetRepository postgresVetRepository, MysqlVisitRepository mysqlVisitRepository, PostgresVisitRepository postgresVisitRepository, MysqlSpecialtyRepository mysqlSpecialtyRepository, PostgresSpecialtyRepository postgresSpecialtyRepository) {
        this.mysqlOwnerRepository = mysqlOwnerRepository;
        this.postgresOwnerRepository = postgresOwnerRepository;
        this.mysqlPetRepository = mysqlPetRepository;
        this.postgresPetRepository = postgresPetRepository;
        this.mysqlVetRepository = mysqlVetRepository;
        this.postgresVetRepository = postgresVetRepository;
        this.mysqlSpecialtyRepository = mysqlSpecialtyRepository;
        this.postgresSpecialtyRepository = postgresSpecialtyRepository;
        this.mysqlVisitRepository = mysqlVisitRepository;
        this.postgresVisitRepository = postgresVisitRepository;
//        this.mysqlPetTypesRepository = mysqlPetTypesRepository;
//        this.postgresPetTypesRepository = postgresPetTypesRepository;
    }

    public void forklift() {
        forkliftOwners();
        forkliftPets();
        forkliftVets();
        forkliftVisits();
        forkliftSpecialties();
        forkliftPetTypes();
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

    public void forkliftPets() {
        List<Pet> pets = mysqlPetRepository.findPets();

        PostgresPet postgresPet;

        for (Pet pet : pets) {

            postgresPet = new PostgresPet();
            postgresPet.setBirthDate(pet.getBirthDate());
            //postgresPet.setType(pet.getType());
            //postgresPet.setOwner(pet.getOwner());
            postgresPetRepository.save(postgresPet);

        }

    }

    public void forkliftVets() {
        Collection<Vet> vets = mysqlVetRepository.findAll();

        PostgresVet postgresVet;

        for (Vet vet : vets) {

            postgresVet = new PostgresVet();
//            postgresVet.setSpecialtiesInternal(vet.getSpecialtiesInternal());
            postgresVetRepository.save(postgresVet);

        }

    }

        public void forkliftSpecialties () {
            List<Specialty> specialties = mysqlSpecialtyRepository.findAll();

            PostgresSpecialty postgresSpecialty;

            for (Specialty specialty : specialties) {
                postgresSpecialty = new PostgresSpecialty();
                postgresSpecialty.setId(specialty.getId());
                postgresSpecialty.setName(specialty.getName());
                postgresSpecialtyRepository.save(postgresSpecialty);
            }
        }

        public void forkliftVisits () {
            List<Visit> visits = mysqlVisitRepository.findAll();

            PostgresVisit postgresVisit;

            for (Visit visit : visits) {

                postgresVisit = new PostgresVisit();
                postgresVisit.setDate(visit.getDate());
                postgresVisit.setDescription(visit.getDescription());
                postgresVisit.setPetId(visit.getPetId());
                postgresVisitRepository.save(postgresVisit);
            }
        }

        public void forkliftPetTypes () {

            PostgresPetType postgresPetType;

    }
}
