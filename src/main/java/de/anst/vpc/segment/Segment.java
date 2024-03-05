package de.anst.vpc.segment;

import java.util.List;

import org.springframework.stereotype.Component;

import de.anst.data.AbstractEntity;
import de.anst.data.JpaCrudService;
import de.anst.ui.ExtComboBoxProvider;
import de.anst.vpc.pearl.Pearl;
import de.anst.vpc.segment.meldepunkt.Meldepunkt;
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
import lombok.extern.java.Log;

@Entity
@Table(name = "segment")
@FieldNameConstants
@AllArgsConstructor
@NoArgsConstructor
public class Segment extends AbstractEntity implements Comparable<Segment>{

	@Getter @Setter
	@NotNull
	@Column(unique = true)
    private String name;
	
	@Getter @Setter
    private String description; 

	@Getter @Setter
	private Long taktZeit;
	
	
	@Getter @Setter
	@OneToMany(mappedBy = "segment", fetch = FetchType.EAGER)
	private List<Meldepunkt> meldepunkte;
	
	@Getter @Setter
	@OneToMany(mappedBy = "segment", fetch = FetchType.EAGER)
	private List<Pearl> pearlChain;
	
	@Override
	public String toString() {
		return name;
	}
	
	@Override
	public int compareTo(Segment o) {
		return getName().compareTo(o.getName());
	}

	@Component
	@Log
	public static class Persister extends JpaCrudService<Segment, Long, SegmentRepository> {
		/**
		 * the long serialVersionUID
		 * since 09.02.2024
		 */
		private static final long serialVersionUID = 868432046536992979L;
		
		private static final String KEIN_SEGMENT_NAME = "Kein Segment";
		
		public static Segment KEIN_SEGMENT;

		private static final String DEMO_SEGMENT_NAME = "Demo";
		
		public static Segment DEMO_SEGMENT;
		
		public ExtComboBoxProvider<Segment> getFormProvider() {
			return new ExtComboBoxProvider<Segment>(repository.findAll(), Segment::getName);
		}
		
		private final SegmentRepository repository;
		
		public Persister(SegmentRepository repository) {
			super(repository);
			this.repository = repository;
			
			log.info("**** " + this.getClass().getName() );
			if (repository.count() == 0) {
				repository.save(new Segment(KEIN_SEGMENT_NAME, "Wenn mal kein Segment benötigt wird", 0l, null, null));
				
				repository.save(new Segment(DEMO_SEGMENT_NAME, "Das Segment für die Demo", 140l, null, null));
				repository.save(new Segment("mitte", "Der mittlere Abschnitt", 140l, null, null));
			}
			
			KEIN_SEGMENT = repository.findByName(KEIN_SEGMENT_NAME).get(0);
			DEMO_SEGMENT = repository.findByName(DEMO_SEGMENT_NAME).get(0);

			log.info(this.getClass().getName() + " has " + repository.count() + " entries, keinSegment is " + KEIN_SEGMENT);
			
		}
		
	}

}
