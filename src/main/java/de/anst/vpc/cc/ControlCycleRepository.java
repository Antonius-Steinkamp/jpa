package de.anst.vpc.cc;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import de.anst.data.SuperRepository;

@RepositoryRestResource(path = "material")
public interface ControlCycleRepository extends SuperRepository<ControlCycle, Long> {
}
