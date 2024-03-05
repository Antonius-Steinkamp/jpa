/**
 * PearlTypeRepository.java created 09.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 */
package de.anst.vpc.pearl.stk;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import de.anst.data.SuperRepository;

/**
 * PearlTypeRepository created 09.02.2024 by
 * <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 *
 */
@RepositoryRestResource(path = "stk")
public interface StkRepository extends SuperRepository<Stk, Long>{

}
