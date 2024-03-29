/**
 * Verbauort.java created 18.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 */
package de.anst.vpc.segment.bdo;

import java.util.List;

import org.springframework.stereotype.Component;

import de.anst.AUtils;
import de.anst.data.AbstractEntity;
import de.anst.data.JpaCrudService;
import de.anst.ui.ExtComboBoxProvider;
import de.anst.vpc.segment.meldepunkt.Meldepunkt;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import lombok.extern.java.Log;

/**
 * Verbauort created 18.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 *
 */
@Entity
@FieldNameConstants
@AllArgsConstructor
@NoArgsConstructor
public class Verbauort extends AbstractEntity implements Comparable<Verbauort>{
	
	@Getter @Setter
	@NotNull
	@Column(unique = true)
	private String name;
	
	@Getter @Setter
	private String description; 
	
	@Getter @Setter
	@ManyToOne
	@JoinColumn(name = "meldepunkt_id", nullable = false)
	private Meldepunkt mp;
	
	@Getter @Setter
	@NotNull
	private Long abstand;
	
	@Override
	public String toString() {
		return name;
	}
	
	@Override
	public int compareTo(Verbauort o) {
		return getName().compareTo(o.getName());
	}
	
	@Component
	@Log
	public static class Persister extends JpaCrudService<Verbauort, Long, VerbauortRepository> {
		
		private static final long serialVersionUID = Persister.class.hashCode();
		
		private final VerbauortRepository repository;
		
		public Persister(VerbauortRepository repository, Meldepunkt.Persister mp) {
			super(repository);
			this.repository = repository;
			
			log.info("**** " + this.getClass().getName() );
			if (repository.count() == 0) {
				Meldepunkt meldepunkt = mp.getDemo100();
				
				for (long l=0; l <5; l++) {
					repository.save(new Verbauort("D100" + l, "D100"+ l,  meldepunkt, l));
				}

				meldepunkt = mp.getDemo500();
				for (long l=0; l <5; l++) {
					repository.save(new Verbauort("D500" + l, "D100"+ l,  meldepunkt, l));
				}

			}
			log.info(this.getClass().getName() + " has " + repository.count() + " entries");
			
		}
		
		public ExtComboBoxProvider<Verbauort> getFormProvider() {
			return new ExtComboBoxProvider<Verbauort>(repository.findAll(), Verbauort::getName);
		}

		public Verbauort findByName(String name) {
			List<Verbauort> findByName = repository.findByName(name);
			if (AUtils.hasValue(findByName)) {
				return findByName.get(0);
			}
			
			return null;
		}
	}
	
	
}
