/**
 * VerbauortRepository.java created 18.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 */
package de.anst.segment.bdo;

import java.util.List;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import de.anst.data.SuperRepository;

/**
 * VerbauortRepository created 18.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 *
 */
@RepositoryRestResource(path = "Verbauort")
public interface VerbauortRepository extends SuperRepository<Verbauort, Long> {
	public List<Verbauort> findByName(String name);
	
	
}
