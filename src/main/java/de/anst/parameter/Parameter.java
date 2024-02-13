/**
 * PearlType.java created 09.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 */
package de.anst.parameter;

import java.time.LocalDateTime;

import de.anst.data.AbstractEntity;
import de.anst.data.JpaCrudService;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import lombok.extern.java.Log;

/**
 * PearlType created 09.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 *
 */

@Entity
@Table(name = "parameter")
@FieldNameConstants
@AllArgsConstructor
public class Parameter  extends AbstractEntity{
	@Getter @Setter
	@NotNull
	@Column(unique = true)
    private String name;
	
	@Getter @Setter
    private String description;
	
	@Getter @Setter
	private Long longValue;
	
	@Getter @Setter
	private String textValue;

	@Getter @Setter
    private LocalDateTime udate; // last update Date

	@Getter @Setter
    private LocalDateTime cdate; // creation Date

    public Parameter() {
    	cdate = LocalDateTime.now();
    	udate = cdate;
    }

    @PreUpdate
    public void preUpdateFunction(){
        udate = LocalDateTime.now();
    }

    @Log
	public static class Persister extends JpaCrudService<Parameter, Long, ParameterRepository> {

		/**
		 * the long serialVersionUID
		 * since 10.02.2024
		 */
		private static final long serialVersionUID = 463193291911543119L;
		
		public static final String UNTRANSLATED_PATTERN = "UntranslatedFormat";  

		private final ParameterRepository repository;
		
		public Persister(ParameterRepository repository) {
			super(repository);
			this.repository = repository;
			if (repository.count() == 0) {
				Parameter type = new Parameter();
				type.setName(UNTRANSLATED_PATTERN);
				type.setTextValue("_%s");
				type.setDescription("Format der unübersetzten Texte");
				repository.save(type);
				
				log.info(repository.count() + " " + this.getClass().getSimpleName() + " saved");
			}
		}
		
		public Parameter findByName(String name) {
			return repository.findByName(name).get(0);
		}
	}

}
