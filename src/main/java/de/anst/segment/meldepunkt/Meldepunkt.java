/**
 * Meldepunkt.java created 18.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 */
package de.anst.segment.meldepunkt;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Component;

import de.anst.data.AbstractEntity;
import de.anst.data.JpaCrudService;
import de.anst.segment.Segment;
import de.anst.ui.ExtComboBoxProvider;
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
 * Meldepunkt created 18.02.2024 by
 * <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 *
 */
@Entity
@FieldNameConstants
@AllArgsConstructor
@NoArgsConstructor
public class Meldepunkt extends AbstractEntity implements Comparable<Meldepunkt> {

	@Getter
	@Setter
	@NotNull
	@Column(unique = true)
	private String name;

	@Getter
	@Setter
	private String description;

	@Getter
	@Setter
	@NotNull
	@ManyToOne
	@JoinColumn(name = "segment_id", nullable = false)
	private Segment segment;

	@Getter
	@Setter
	@NotNull
	private Long taktzeit;

	@Getter
	@Setter
	@NotNull
	private Long eigenschaften;

	@Override
	public String toString() {
		return getLabel();
	}
	
	public String getLabel() {
		return name + " - " + segment.getName();
	}

	@Override
	public int compareTo(Meldepunkt o) {
		return getName().compareTo(o.getName());
	}

	@Component
	@Log
	public static class Persister extends JpaCrudService<Meldepunkt, Long, MeldepunktRepository> {

		/**
		 * String DCEMO_WEG {@value #DCEMO_WEG}
		 * since 18.02.2024
		 */
		private static final String DCEMO_WEG = "DWEG";
		/**
		 * String DEMO_500 {@value #DEMO_500}
		 * since 18.02.2024
		 */
		private static final String DEMO_500 = "D500";
		/**
		 * String DEMO_100 {@value #DEMO_100}
		 * since 18.02.2024
		 */
		private static final String DEMO_100 = "D100";
		/**
		 * long MACHTNIX {@value #MACHTNIX}
		 * since 18.02.2024
		 */
		private static final long MACHTNIX = 0l;
		/**
		 * long UNLINK {@value #UNLINK}
		 * since 18.02.2024
		 */
		private static final long UNLINK = 4l;
		/**
		 * long FORTSCHRITT {@value #FORTSCHRITT} 
		 * since 18.02.2024
		 */
		private static final long FORTSCHRITT = 2l;
		/**
		 * long SEQUENZBILDEND since 18.02.2024
		 */
		private static final long SEQUENZBILDEND = 1l;

		private static final long serialVersionUID = Persister.class.hashCode();
		
		@Getter
		private Meldepunkt demo100;
		
		@Getter
		private Meldepunkt demo500;
		
		@Getter
		private final ExtComboBoxProvider<Meldepunkt> provider;

		public Persister(MeldepunktRepository repository, Segment.Persister sPersister) {
			super(repository);
			log.info("**** " + this.getClass().getName());
			if (repository.count() == 0) {
				repository.save(new Meldepunkt("NO", "No meldepunkt", sPersister.getKeinSegment(), 0l, MACHTNIX));
				repository.save(new Meldepunkt(DEMO_100, "Sequenzbildend", sPersister.getDemoSegment(), 140l, SEQUENZBILDEND));
				repository.save(new Meldepunkt(DEMO_500, "Fortschritt", sPersister.getDemoSegment(), 140l, FORTSCHRITT));
				repository.save(new Meldepunkt(DCEMO_WEG, "Entfernen", sPersister.getDemoSegment(), 0l, UNLINK));
			}
			
			demo100 = repository.findByName(DEMO_100).get(0);
			demo500 = repository.findByName(DEMO_500).get(0);
			
			provider = new ExtComboBoxProvider<>(findAll(), Meldepunkt::getLabel);
			
			log.info(this.getClass().getName() + " has " + repository.count() + " entries");

		}

	}

}
