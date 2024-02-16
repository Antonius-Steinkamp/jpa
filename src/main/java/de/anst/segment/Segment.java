package de.anst.segment;

import org.springframework.stereotype.Component;

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
		
		public Persister(SegmentRepository repository) {
			super(repository);
			log.info("**** " + this.getClass().getName() );
			if (repository.count() == 0) {
				repository.save(new Segment("mitte", "Der mittlere Abschnitt", 140l));
			}
			log.info(this.getClass().getName() + " has " + repository.count() + " entries");
			
		}
		
	}

}
