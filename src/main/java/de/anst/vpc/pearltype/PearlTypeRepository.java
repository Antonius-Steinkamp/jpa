/**
 * PearlTypeRepository.java created 09.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 */
package de.anst.vpc.pearltype;

import java.util.List;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import de.anst.data.SuperRepository;

/**
 * PearlTypeRepository created 09.02.2024 by
 * <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 *
 */
@RepositoryRestResource(path = "pearltype")
public interface PearlTypeRepository extends SuperRepository<PearlType, Long>{
	public List<PearlType> findByName(String name);

}
