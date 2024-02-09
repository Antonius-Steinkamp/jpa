package de.anst.i18n;

import java.time.LocalDateTime;

import de.anst.data.AbstractEntity;
import de.anst.data.JpaCrudService;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PreUpdate;
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
    private String original;

	@Getter @Setter
	@NotNull
    private String locale;

	@Getter @Setter
	@NotNull
    @Column(unique = true)
    private String translated;

	@Getter @Setter
    private LocalDateTime udate; // last update Date

	@Getter @Setter
    private LocalDateTime cdate; // creation Date

	@Getter @Setter
    private LocalDateTime rdate; // last read Date

    public Translation() {
    	cdate = LocalDateTime.now();
    	udate = cdate;
    }
    
    @PreUpdate
    public void preUpdateFunction(){
        udate = LocalDateTime.now();
    }
    
	public static class Persister extends JpaCrudService<Translation, Long, TranslationRepository> {

		/**
		 * the long serialVersionUID
		 * since 09.02.2024
		 */
		private static final long serialVersionUID = -6269605735032634535L;

		public Persister(TranslationRepository repository) {
			super(repository);
		}
	}


}
