/**
 * PearlType.java created 09.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 */
package de.anst.pearl.type;

import de.anst.data.AbstractEntity;
import de.anst.data.JpaCrudService;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

/**
 * PearlType created 09.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 *
 */

@Entity
@Table(name = "pearltype")
@FieldNameConstants
@AllArgsConstructor
@NoArgsConstructor
public class PearlType  extends AbstractEntity{
	@Getter @Setter
	@NotNull
	@Column(unique = true)
    private String name;
	
	@Getter @Setter
    private String description;

	@Override
	public String toString() {
		return name;
	}

	public static class Persister extends JpaCrudService<PearlType, Long, PearlTypeRepository> {

		/**
		 * the long serialVersionUID
		 * since 09.02.2024
		 */
		private static final long serialVersionUID = -233320722748488019L;

		protected final PearlTypeRepository repository;
		public Persister(PearlTypeRepository repository) {
			super(repository);
			this.repository = repository;
			if (repository.count() == 0) {
				PearlType type = new PearlType();
				type.setName("Cabrio");
				type.setDescription("Das hübsche Cabrio mit dem Faltdach");
				repository.save(type);
			}
		}
	}

}