/**
 * PearlType.java created 09.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 */
package de.anst.vpc.pearl;

import java.util.Collection;

import org.springframework.stereotype.Component;

import de.anst.data.AbstractEntity;
import de.anst.data.JpaCrudService;
import de.anst.ui.ExtComboBoxProvider;
import de.anst.vpc.pearl.stk.Stk;
import de.anst.vpc.pearltype.PearlType;
import de.anst.vpc.segment.Segment;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

/**
 * PearlType created 09.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 *
 */

@Entity
@Table(name = "pearl", uniqueConstraints = {
		@UniqueConstraint (columnNames = {"name", "segment"}, name="name_segment")
		})

@FieldNameConstants
@NoArgsConstructor
public class Pearl  extends AbstractEntity implements Comparable<Pearl>{
	@Getter @Setter
	@NotNull
    private String name;
	
	@Getter @Setter
	@ManyToOne
	@NotNull
    private PearlType type;

	@Getter @Setter
	@ManyToOne
	@NotNull
    private Segment segment;

	@Getter @Setter
	private Long pos; // in Chain

	@OneToMany(mappedBy = "pearl", fetch = FetchType.EAGER)
	@Getter @Setter
	private Collection<Stk> ccListe;

	@Override
	public int compareTo(Pearl o) {
		if ( o == null) {
			return 0;
		}
		return getName().compareTo(o.getName());
	}

	@Override
	public String toString() {
		return name + "/" + segment.getName();
	}

	
	@Component
	public static class Persister extends JpaCrudService<Pearl, Long,PearlRepository> {
		
		private static final long serialVersionUID = Pearl.Persister.class.hashCode();
		
		public ExtComboBoxProvider<Pearl> getFormProvider() {
			return new ExtComboBoxProvider<Pearl>(repository.findAll(), Pearl::getName);
		}
		
		private final PearlRepository repository;
		
		public Persister(PearlRepository repository) {
			super(repository);
			this.repository = repository;
			
			if (repository.count() == 0) {
				// Generate Data (if necessary)
			}
		}
	}
	

}
