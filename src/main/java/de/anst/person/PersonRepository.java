package de.anst.person;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "material")
public interface PersonRepository extends JpaRepository<Person, Long>, JpaSpecificationExecutor<Person> {
	@Query(value = "select distinct(" + Person.Fields.occupation + ") from Person")
	List<String> findAllOccupations();
	
	@Query(value = "select distinct(" + Person.Fields.role + ") from Person")
	List<String> findAllRoles();
}
