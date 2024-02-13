package de.anst.i18n;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Component;

import de.anst.data.AbstractEntity;
import de.anst.data.JpaCrudService;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;

@Entity
@FieldNameConstants
@ToString
public class Translation extends AbstractEntity {

	@Getter @Setter
	@NotNull
    @Column(unique = true)
    private String original;

	@Getter @Setter
	@NotNull
    private String locale;

	@Getter @Setter
	@NotNull
    private String translated;


	@Getter @Setter
    private LocalDateTime rdate; // last read Date

  
	@Component
	public static class Persister extends JpaCrudService<Translation, Long, TranslationRepository> {

		/**
		 * the long serialVersionUID
		 * since 09.02.2024
		 */
		private static final long serialVersionUID = -6269605735032634535L;

		public static final Locale[] knownLocales = {new Locale("de"), new Locale("en"), new Locale("fi"), new Locale("fr")}; 

		private final TranslationRepository repository;
		public Persister(TranslationRepository repository) {
			super(repository);
			this.repository = repository;
		}
		
		public List<Translation> findByOriginalAndLocale(String original, String locale) {
			return repository.findByOriginalAndLocale(original, locale);
		}

	}

}
