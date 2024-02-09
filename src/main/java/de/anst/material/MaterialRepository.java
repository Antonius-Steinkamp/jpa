package de.anst.material;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "material")
public interface MaterialRepository
        extends
            JpaRepository<Material, Long>,
            JpaSpecificationExecutor<Material> {

}
