/**
 * PearlType.java created 09.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 */
package de.anst.vpc.pearltype;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Component;

import de.anst.data.AbstractEntity;
import de.anst.data.JpaCrudService;
import de.anst.ui.ExtComboBoxProvider;
import de.anst.vpc.pearltype.stkelement.StkElement;
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
public class PearlType  extends AbstractEntity implements Comparable<PearlType>{
	@Getter @Setter
	@NotNull
	@Column(unique = true)
    private String name;
	
	@Getter @Setter
    private String description;

	@OneToMany(mappedBy = "pearltype", fetch = FetchType.EAGER)
	@Getter @Setter
	private Collection<StkElement> stkListe;

	@Override
	public int compareTo(PearlType o) {
		if ( o == null) {
			return 0;
		}
		return getName().compareTo(o.getName());
	}

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

		public ExtComboBoxProvider<PearlType> getFormProvider() {
			return new ExtComboBoxProvider<PearlType>(repository.findAll(), PearlType::getName);
		}

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
		
		public List<PearlType> findByName(String name) {
			return repository.findByName(name);
		}
		
		public List<String> findNames() {
			return repository.getTypeNames();
		}
		
	}


}
