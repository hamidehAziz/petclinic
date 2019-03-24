package org.springframework.samples.petclinic.postgres.repo;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.Repository;
import org.springframework.samples.petclinic.postgres.domain.PostgresSpecialty;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import java.util.Collection;

public interface PostgresSpecialtyRepository extends Repository<PostgresSpecialty, Integer> {

    @Transactional(readOnly = true)
    Collection<PostgresSpecialty> findAll() throws DataAccessException;

    void save(PostgresSpecialty specialty);

    List<PostgresSpecialty> findSpecialties();

}
