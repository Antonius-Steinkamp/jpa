package de.anst.material;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "einheit")
public interface EinheitRepository extends JpaRepository<Einheit, Long>, JpaSpecificationExecutor<Einheit> {
	public List<Einheit> findByName(String name);


}
