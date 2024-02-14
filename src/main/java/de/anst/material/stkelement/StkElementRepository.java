package de.anst.material.stkelement;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "stkelement")
public interface StkElementRepository extends JpaRepository<StkElement, Long>, JpaSpecificationExecutor<StkElement> {

}
