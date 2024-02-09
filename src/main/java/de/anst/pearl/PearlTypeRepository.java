/**
 * PearlTypeRepository.java created 09.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 */
package de.anst.pearl;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * PearlTypeRepository created 09.02.2024 by
 * <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 *
 */
@RepositoryRestResource(path = "pearltype")
public interface PearlTypeRepository extends JpaRepository<PearlType, Long>, JpaSpecificationExecutor<PearlType> {
	public List<PearlType> findByName(String name);

}
