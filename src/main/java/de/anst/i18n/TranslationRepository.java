package de.anst.i18n;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TranslationRepository extends JpaRepository<Translation, Long>, JpaSpecificationExecutor<Translation> {

}
