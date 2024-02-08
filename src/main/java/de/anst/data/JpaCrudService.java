/**
 * JpsCrudService.java created 07.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 */
package de.anst.data;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.vaadin.crudui.crud.CrudListener;

import lombok.RequiredArgsConstructor;

/**
 * JpsCrudService created 07.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 *
 */
@RequiredArgsConstructor
public class JpaCrudService<T,I, V extends JpaRepository<T, I> & JpaSpecificationExecutor<T>> implements CrudListener<T>{
	
	/**
	 * the long serialVersionUID
	 * since 07.02.2024
	 */
	private static final long serialVersionUID = -4142089083549385560L;
	
	private final V repositoy;

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
	
    public Optional<T> get(I id) {
        return repositoy.findById(id);
    }

    public void deleteById(I id) {
    	repositoy.deleteById(id);
    }

    public Page<T> list(Pageable pageable) {
        return repositoy.findAll(pageable);
    }

    public Page<T> list(Pageable pageable, Specification<T> filter) {
        return repositoy.findAll(filter, pageable);
    }

    public int count() {
        return (int) repositoy.count();
    }

}
