/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.postgres.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.samples.petclinic.postgres.domain.PostgresPet;
import org.springframework.samples.petclinic.postgres.domain.PostgresPetType;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Repository class for <code>Pet</code> domain objects All method names are compliant with Spring Data naming
 * conventions so this interface can easily be extended for Spring Data See here: http://static.springsource.org/spring-data/jpa/docs/current/reference/html/jpa.repositories.html#jpa.query-methods.query-creation
 *
 * @author Ken Krebs
 * @author Juergen Hoeller
 * @author Sam Brannen
 * @author Michael Isvy
 */
public interface PostgresPetRepository extends Repository<PostgresPet, Integer> {

    /**
     * Retrieve all {@link PostgresPetType}s from the data store.
     * @return a Collection of {@link PostgresPetType}s.
     */
    @Query("SELECT ptype FROM PostgresPetType ptype ORDER BY ptype.name")
    @Transactional(readOnly = true)
    List<PostgresPetType> findPetTypes();

    /**
     * Retrieve a {@link PostgresPet} from the data store by id.
     * @param id the id to search for
     * @return the {@link PostgresPet} if found
     */
    @Transactional(readOnly = true)
    PostgresPet findById(Integer id);

    /**
     * Save a {@link PostgresPet} to the data store, either inserting or updating it.
     * @param pet the {@link PostgresPet} to save
     */
    void save(PostgresPet pet);

    List<PostgresPet> findPets();

}

