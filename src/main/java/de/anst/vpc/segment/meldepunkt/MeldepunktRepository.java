/**
 * MeldepunktRepository.java created 18.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 */
package de.anst.vpc.segment.meldepunkt;

import java.util.List;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import de.anst.data.SuperRepository;

/**
 * MeldepunktRepository created 18.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 *
 */
@RepositoryRestResource(path = "Meldepunkt")
public interface MeldepunktRepository extends SuperRepository<Meldepunkt, Long> {
	public List<Meldepunkt> findByName(String name);

}

