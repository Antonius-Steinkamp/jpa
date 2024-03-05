package de.anst.vpc.pearltype.stkelement;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import de.anst.data.SuperRepository;

@RepositoryRestResource(path = "stkelement")
public interface StkElementRepository extends SuperRepository<StkElement, Long>{

}
