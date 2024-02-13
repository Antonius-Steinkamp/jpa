package de.anst.example;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "example")
public interface ExampleRepository extends JpaRepository<Example, Long>, JpaSpecificationExecutor<Example> {
	public List<Example> findByName(String name);


}
