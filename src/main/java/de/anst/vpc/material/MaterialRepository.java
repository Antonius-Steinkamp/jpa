package de.anst.vpc.material;

import java.util.List;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import de.anst.data.SuperRepository;

@RepositoryRestResource(path = "material")
public interface MaterialRepository extends SuperRepository<Material, Long> {
	List<Material> findByName(String name);
}
