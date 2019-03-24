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
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.postgres.domain.PostgresOwner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

/**
 * Repository class for <code>Owner</code> domain objects All method names are compliant with Spring Data naming
 * conventions so this interface can easily be extended for Spring Data See here: http://static.springsource.org/spring-data/jpa/docs/current/reference/html/jpa.repositories.html#jpa.query-methods.query-creation
 *
 * @author Ken Krebs
 * @author Juergen Hoeller
 * @author Sam Brannen
 * @author Michael Isvy
 */
public interface PostgresOwnerRepository extends Repository<PostgresOwner, Integer> {

    /**
     * Retrieve {@link PostgresOwner}s from the data store by last name, returning all owners
     * whose last name <i>starts</i> with the given name.
     * @param lastName Value to search for
     * @return a Collection of matching {@link PostgresOwner}s (or an empty Collection if none
     * found)
     */
    @Query("SELECT DISTINCT owner FROM PostgresOwner owner left join fetch owner.pets WHERE owner.lastName LIKE :lastName%")
    @Transactional(readOnly = true)
    Collection<PostgresOwner> findByLastName(@Param("lastName") String lastName);

    /**
     * Retrieve an {@link PostgresOwner} from the data store by id.
     * @param id the id to search for
     * @return the {@link PostgresOwner} if found
     */
    @Query("SELECT owner FROM PostgresOwner owner left join fetch owner.pets WHERE owner.id =:id")
    @Transactional(readOnly = true)
    PostgresOwner findById(@Param("id") Integer id);

    /**
     * Save an {@link PostgresOwner} to the data store, either inserting or updating it.
     * @param owner the {@link PostgresOwner} to save
     */
    void save(PostgresOwner owner);

    List<PostgresOwner> findAll();


}
