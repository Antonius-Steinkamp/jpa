/**
 * PearlTypeRepository.java created 09.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 */
package de.anst.vpc.fis;

import java.util.List;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import de.anst.data.SuperRepository;

/**
 * PearlTypeRepository created 09.02.2024 by
 * <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 *
 */
@RepositoryRestResource(path = "fismessage")
public interface FisMessageRepository extends SuperRepository<FisMessage, Long>{
	public List<FisMessage> findByNameAndMpName(String name, String mp);

}
