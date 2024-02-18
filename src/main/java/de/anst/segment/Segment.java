package de.anst.segment;

import java.util.List;

import org.springframework.stereotype.Component;

import de.anst.data.AbstractEntity;
import de.anst.data.JpaCrudService;
import de.anst.segment.meldepunkt.Meldepunkt;
import de.anst.ui.ExtComboBoxProvider;
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
		
		private static final String KEIN_SEGMENT = "Kein Segment";
		
		@Getter
		private Segment keinSegment;

		private static final String DEMO_SEGMENT = "Demo";
		
		@Getter
		private Segment demoSegment;
		
		@Getter
		private final ExtComboBoxProvider<Segment> provider;
		
		public Persister(SegmentRepository repository) {
			super(repository);
			log.info("**** " + this.getClass().getName() );
			if (repository.count() == 0) {
				repository.save(new Segment(KEIN_SEGMENT, "Wenn mal kein Segment benötigt wird", 0l, null));
				repository.save(new Segment(DEMO_SEGMENT, "Das Segment für die Demo", 140l, null));
				repository.save(new Segment("mitte", "Der mittlere Abschnitt", 140l, null));
			}
			
			keinSegment = repository.findByName(KEIN_SEGMENT).get(0);
			demoSegment = repository.findByName(DEMO_SEGMENT).get(0);
			provider = new ExtComboBoxProvider<Segment>(repository.findAll(), Segment::getName);

			log.info(this.getClass().getName() + " has " + repository.count() + " entries, keinSegment is " + keinSegment);
			
		}
		
	}

}
