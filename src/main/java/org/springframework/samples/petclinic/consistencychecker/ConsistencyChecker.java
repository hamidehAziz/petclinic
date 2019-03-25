package org.springframework.samples.petclinic.consistencychecker;
import org.springframework.samples.petclinic.mysql.domain.Owner;
import org.springframework.samples.petclinic.mysql.domain.Pet;
import org.springframework.samples.petclinic.mysql.domain.Vet;
import org.springframework.samples.petclinic.mysql.domain.Specialty;
import org.springframework.samples.petclinic.mysql.domain.Visit;
import org.springframework.samples.petclinic.mysql.repo.MysqlOwnerRepository;
import org.springframework.samples.petclinic.mysql.repo.MysqlPetRepository;
import org.springframework.samples.petclinic.mysql.repo.MysqlVetRepository;
import org.springframework.samples.petclinic.mysql.repo.MysqlSpecialtyRepository;
import org.springframework.samples.petclinic.mysql.repo.MysqlVisitRepository;
import org.springframework.samples.petclinic.mysql.repo.MysqlPetTypesRepository;
import org.springframework.samples.petclinic.postgres.domain.*;
import org.springframework.samples.petclinic.postgres.repo.PostgresOwnerRepository;
import org.springframework.samples.petclinic.postgres.repo.PostgresPetRepository;
import org.springframework.samples.petclinic.postgres.repo.PostgresVetRepository;
import org.springframework.samples.petclinic.postgres.repo.PostgresSpecialtyRepository;
import org.springframework.samples.petclinic.postgres.repo.PostgresVisitRepository;
import org.springframework.samples.petclinic.postgres.repo.PostgresPetTypesRepository;
import org.springframework.stereotype.Component;


import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class ConsistencyChecker {


    private MysqlOwnerRepository mysqlOwnerRepository;
    private MysqlPetRepository mysqlPetRepository;
    private MysqlVetRepository mysqlVetRepository;
    private MysqlSpecialtyRepository mysqlSpecialtyRepository;
    private MysqlVisitRepository mysqlVisitRepository;
    private MysqlPetTypesRepository mysqlPetTypesRepository;


    private PostgresOwnerRepository postgresOwnerRepository;
    private PostgresPetRepository postgresPetRepository;
    private PostgresVetRepository postgresVetRepository;
    private PostgresSpecialtyRepository postgresSpecialtyRepository;
    private PostgresVisitRepository postgresVisitRepository;
    private PostgresPetTypesRepository postgresPetTypesRepository;


    public ConsistencyChecker(MysqlOwnerRepository mysqlOwnerRepository, PostgresOwnerRepository postgresOwnerRepository, MysqlPetRepository mysqlPetRepository, PostgresPetRepository postgresPetRepository, MysqlVetRepository mysqlVetRepository, PostgresVetRepository postgresVetRepository, MysqlVisitRepository mysqlVisitRepository, PostgresVisitRepository postgresVisitRepository, MysqlSpecialtyRepository mysqlSpecialtyRepository, PostgresSpecialtyRepository postgresSpecialtyRepository, MysqlPetTypesRepository mysqlPetTypesRepository, PostgresPetTypesRepository postgresPetTypesRepository) {
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
        this.mysqlPetTypesRepository = mysqlPetTypesRepository;
        this.postgresPetTypesRepository = postgresPetTypesRepository;
    }

    public int ownerConsistencyChecker(){

        List<Owner> ownersSql = mysqlOwnerRepository.findAll();
        List<PostgresOwner> ownersPost = postgresOwnerRepository.findAll();
        int inconsistencyOwner = 0;
        for ( Owner ownerSql: ownersSql)
        for (PostgresOwner ownerPost: ownersPost)
        {

            Integer idExpected = ownerSql.getId();
            String firstNameExpected = ownerSql.getFirstName();
            String lastNameExpected = ownerSql.getLastName();
            String addressExpected = ownerSql.getAddress();
            String cityExpected = ownerSql.getCity();
            String telephoneExpected = ownerSql.getTelephone();

            Integer idActual = ownerPost.getId();
            String firstNameActual = ownerPost.getFirstName();
            String lastNameActual = ownerPost.getLastName();
            String addressActual= ownerPost.getAddress();
            String cityActual = ownerPost.getCity();
            String telephoneActual = ownerPost.getTelephone();

            if (!idExpected.equals(idActual)){
                inconsistencyOwner++;

                //correct it

                ownerPost.setId(idExpected);

            }

            if (!firstNameExpected.equals(firstNameActual)){
                inconsistencyOwner++;

                //correct it

                ownerPost.setFirstName(firstNameExpected);
            }

            if (!lastNameExpected.equals(lastNameActual)){
                inconsistencyOwner++;

                //correct it

                ownerPost.setLastName(lastNameExpected);
            }

            if (!addressExpected.equals(addressActual)){
                inconsistencyOwner++;

                //correct it

                ownerPost.setAddress(addressActual);
            }

            if (!cityExpected.equals(cityActual)){
                inconsistencyOwner++;

                //correct it

                ownerPost.setCity(cityExpected);

            }

            if (!telephoneExpected.equals(telephoneActual)){
                inconsistencyOwner++;

                //correct it
                ownerPost.setTelephone(telephoneExpected);
            }


        }
        System.out.println(inconsistencyOwner);
        return inconsistencyOwner;

    }



    public int specialtiesConsistencyChecker(){
        List<Specialty> specialtiesSql = mysqlSpecialtyRepository.findAll();
        List<PostgresSpecialty> specialtiesPost = postgresSpecialtyRepository.findSpecialties();
        int inconsistencySpecialty = 0;


        for ( Specialty specialtySql: specialtiesSql)
            for (PostgresSpecialty specialtyPost: specialtiesPost)
            {

                Integer idExpected = specialtySql.getId();
                String nameExpected = specialtySql.getName();



                Integer idActual = specialtyPost.getId();
                String nameActual = specialtyPost.getName();


                if (!idExpected.equals(idActual)){
                    inconsistencySpecialty++;

                    //correct it
                    specialtyPost.setId(idExpected);

                }

                if (!nameExpected.equals(nameActual)){
                    inconsistencySpecialty++;

                    //correct it
                    specialtyPost.setName(nameExpected);

                }

            }
        return inconsistencySpecialty;


    }

    public int visitsConsistencyChecker(){

        List<Visit> visitsSql = mysqlVisitRepository.findAll();
        List<PostgresVisit> visitsPost = postgresVisitRepository.findAll();
        int inconsistencyVisit= 0;

        for ( Visit visitSql: visitsSql)
            for (PostgresVisit visitPost: visitsPost){

                LocalDate dateExpected = visitSql.getDate();
                String descriptionExpected = visitSql.getDescription();
                Integer petIdExpected = visitSql.getPetId();

                LocalDate dateActual = visitPost.getDate();
                String descriptionActual= visitPost.getDescription();
                Integer petIdActual = visitPost.getPetId();

                if (!dateExpected.equals(dateActual)){
                    inconsistencyVisit++;

                    //correct it
                    visitPost.setDate(dateExpected);

                }

                if (!descriptionExpected.equals(descriptionActual)){
                    inconsistencyVisit++;

                    //correct it
                    visitPost.setDescription(descriptionExpected);

                }

                if (!petIdExpected.equals(petIdActual)){
                    inconsistencyVisit++;

                    //correct it
                    visitPost.setPetId(petIdExpected);

                }


            }

            return inconsistencyVisit;


    }



}
