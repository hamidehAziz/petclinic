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
            String lastNameExpected = ownerSql.getFirstName();
            String addressExpected = ownerSql.getAddress();
            String cityExpected = ownerSql.getCity();
            String telephoneExpected = ownerSql.getTelephone();

            Integer idActual = ownerPost.getId();
            String firstNameActual = ownerPost.getFirstName();
            String lastNameActual = ownerPost.getFirstName();
            String addressActual= ownerPost.getAddress();
            String cityActual = ownerPost.getCity();
            String telephoneActual = ownerPost.getTelephone();

            if (!idExpected.equals(idActual)){
                inconsistencyOwner++;

                //correct it

                idActual = idExpected;

            }

            if (!firstNameExpected.equals(firstNameActual)){
                inconsistencyOwner++;

                //correct it

                firstNameActual = firstNameExpected;
            }

            if (!lastNameExpected.equals(lastNameActual)){
                inconsistencyOwner++;

                //correct it

                lastNameActual = firstNameExpected;
            }

            if (!addressExpected.equals(addressActual)){
                inconsistencyOwner++;

                //correct it

                addressActual = addressExpected;
            }

            if (!cityExpected.equals(cityActual)){
                inconsistencyOwner++;

                //correct it

                cityActual = cityExpected;

            }

            if (!telephoneExpected.equals(telephoneActual)){
                inconsistencyOwner++;

                //correct it
                telephoneActual = telephoneExpected;
            }


        }
        System.out.println(inconsistencyOwner);
        return inconsistencyOwner;

    }

    public void petConsistencyChecker(){


    }

    public void vetConsistencyChecker(){

    }

    public void specialtiesConsistencyChecker(){



    }

    public void visitsConsistencyChecker(){

    }



}
