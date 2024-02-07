/**
 * SampleService.java created 07.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 */
package de.anst.data;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import lombok.RequiredArgsConstructor;

/**
 * SampleService created 07.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 *
 */
@RequiredArgsConstructor
public class BaseService<T, I, V extends JpaRepository<T, I> & JpaSpecificationExecutor<T> > {

	private final V  repository;
	
	
    public Optional<T> get(I id) {
        return repository.findById(id);
    }

    public T update(T entity) {
        return repository.save(entity);
    }

    public void delete(I id) {
        repository.deleteById(id);
    }

    public Page<T> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Page<T> list(Pageable pageable, Specification<T> filter) {
        return repository.findAll(filter, pageable);
    }

    public int count() {
        return (int) repository.count();
    }

}
