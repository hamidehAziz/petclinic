package org.springframework.samples.petclinic.mysql.repo;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.Repository;
import org.springframework.samples.petclinic.mysql.domain.BaseEntity;
import org.springframework.samples.petclinic.mysql.domain.Specialty;

import java.util.List;

public interface MysqlSpecialtyRepository extends Repository<Specialty, Integer> {

    List<Specialty> findAll();

}
