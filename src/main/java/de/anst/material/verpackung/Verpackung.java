package de.anst.material.verpackung;

import org.springframework.stereotype.Component;

import de.anst.data.AbstractEntity;
import de.anst.data.JpaCrudService;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

@Entity
@Table(name = "verpackung")
@FieldNameConstants
@NoArgsConstructor
public class Verpackung extends AbstractEntity implements Comparable<Verpackung>{

	@Getter @Setter
	@NotNull
	@Column(unique = true)
    private String name;
	
	@Getter @Setter
    private Double laenge = Double.valueOf(0);
	@Getter @Setter
    private Double hoehe = Double.valueOf(0);
	@Getter @Setter
    private Double breite = Double.valueOf(0);

	@Getter @Setter
    private Double gewicht = Double.valueOf(0);

	@Override
	public String toString() {
		return name;
	}

	@Override
	public int compareTo(Verpackung o) {
		return getName().compareTo(o.name);
	}

	public Verpackung(String name) {
		this.name = name;
	}
	
	@Component
	public static class Persister extends JpaCrudService<Verpackung, Long, VerpackungRepository> {
		/**
		 * the long serialVersionUID
		 * since 09.02.2024
		 */
		private static final long serialVersionUID = 868432046536992979L;

		public Persister(VerpackungRepository repository) {
			super(repository);
			if (repository.count() == 0) {
				repository.save(new Verpackung("Karton"));
				repository.save(new Verpackung("Kiste"));
				repository.save(new Verpackung("Palette"));
				repository.save(new Verpackung("Eimer"));
				repository.save(new Verpackung("Faß"));
			}
		}
	}

}
