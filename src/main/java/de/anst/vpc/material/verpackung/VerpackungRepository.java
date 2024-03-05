package de.anst.vpc.material.verpackung;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "verpackung")
public interface VerpackungRepository extends JpaRepository<Verpackung, Long>, JpaSpecificationExecutor<Verpackung> {
	
	public List<Verpackung> findByName(String name);

}
