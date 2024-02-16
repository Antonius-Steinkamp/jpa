/**
 * SuperRepository.java created 15.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 */
package de.anst.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * SuperRepository created 15.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 *
 */
@NoRepositoryBean
public interface SuperRepository<T,I> extends JpaRepository<T, I>, JpaSpecificationExecutor<T> {

}
