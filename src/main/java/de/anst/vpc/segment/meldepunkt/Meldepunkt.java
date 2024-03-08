/**
 * Meldepunkt.java created 18.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 */
package de.anst.vpc.segment.meldepunkt;

import java.util.List;

import org.springframework.stereotype.Component;

import de.anst.data.AbstractEntity;
import de.anst.data.JpaCrudService;
import de.anst.ui.ExtComboBoxProvider;
import de.anst.vpc.material.einheit.Einheit;
import de.anst.vpc.pearl.Pearl;
import de.anst.vpc.segment.Segment;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.ReportAsSingleViolation;
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

	@Getter	@Setter
	@NotNull
	private Long eigenschaften;

	@Getter	@Setter
	@OneToOne
	private Meldepunkt nachfolger;
	
	@Getter @Setter
    @ManyToOne
	private Pearl pearl;

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
		
		public ExtComboBoxProvider<Meldepunkt> getProvider() {
			return new ExtComboBoxProvider<>(findAll(), Meldepunkt::getLabel);
		}

		private final MeldepunktRepository repository;
		public Persister(MeldepunktRepository repository, Segment.Persister sPersister) {
			super(repository);
			this.repository = repository;
			
			log.info("**** " + this.getClass().getName());
			if (repository.count() == 0) {
				repository.save(new Meldepunkt("NO", "No meldepunkt", Segment.Persister.KEIN_SEGMENT, 0l, MACHTNIX, null, null));
				Meldepunkt m500 = repository.save(new Meldepunkt(DEMO_500, "Fortschritt", Segment.Persister.DEMO_SEGMENT, 140l, FORTSCHRITT, null, null));
				repository.save(new Meldepunkt(DEMO_100, "Sequenzbildend", Segment.Persister.DEMO_SEGMENT, 140l, SEQUENZBILDEND, m500, null));
				repository.save(new Meldepunkt(DCEMO_WEG, "Entfernen", Segment.Persister.DEMO_SEGMENT, 0l, UNLINK, null, null));
			}
			
			demo100 = repository.findByName(DEMO_100).get(0);
			demo500 = repository.findByName(DEMO_500).get(0);
			
			log.info(this.getClass().getName() + " has " + repository.count() + " entries");

		}
		
		public List<Meldepunkt> findByName(String name) {
			return repository.findByName(name);
		}

	}

}
