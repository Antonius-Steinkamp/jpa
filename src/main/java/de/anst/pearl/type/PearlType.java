/**
 * PearlType.java created 09.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 */
package de.anst.pearl.type;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import de.anst.data.AbstractEntity;
import de.anst.data.JpaCrudService;
import de.anst.material.stkelement.StkElement;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
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

	@OneToMany(mappedBy = "pearltype", fetch = FetchType.EAGER)
	@Getter @Setter
	private Collection<StkElement> stkListe = new ArrayList<>();
	
	@Override
	public String toString() {
		return name;
	}

	public PearlType(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	@Component
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
				PearlType type;
				type = new PearlType("Cabrio", "Das Cabrio mit dem Faltdach"); repository.save(type);
				type = new PearlType("Limousine", "Das elegante Gerät"); repository.save(type);
				type = new PearlType("Limousine klein", "Das elegante kleine Gerät"); repository.save(type);
				type = new PearlType("Limousine mittel", "Das elegante mittlere Gerät"); repository.save(type);
				type = new PearlType("Avant", "Der schicke Kombi"); repository.save(type);
			}
		}
	}

}
