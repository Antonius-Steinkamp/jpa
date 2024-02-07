/**
 * JpsCrudService.java created 07.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 */
package de.anst.data;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vaadin.crudui.crud.CrudListener;

import lombok.RequiredArgsConstructor;

/**
 * JpsCrudService created 07.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 *
 */
@RequiredArgsConstructor
public class JpaCrudService<T,I> implements CrudListener<T>{
	
	/**
	 * the long serialVersionUID
	 * since 07.02.2024
	 */
	private static final long serialVersionUID = -4142089083549385560L;
	
	private final JpaRepository<T, I> repositoy;

	@Override
	public Collection<T> findAll() {
		return repositoy.findAll();
	}

	@Override
	public T add(T domainObjectToAdd) {
		return repositoy.save(domainObjectToAdd);
	}

	@Override
	public T update(T domainObjectToUpdate) {
		return repositoy.save(domainObjectToUpdate);
	}

	@Override
	public void delete(T domainObjectToDelete) {
		repositoy.delete(domainObjectToDelete);
		
	}

}
