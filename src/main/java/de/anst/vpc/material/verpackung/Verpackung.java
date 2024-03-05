package de.anst.vpc.material.verpackung;

import org.springframework.stereotype.Component;

import de.anst.data.AbstractEntity;
import de.anst.data.JpaCrudService;
import de.anst.ui.ExtComboBoxProvider;
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
		 * String UNBEKANNT_NAME {@value #UNBEKANNT_NAME}
		 * since 28.02.2024
		 */
		private static final String UNBEKANNT_NAME = "Unbekannt";

		/**
		 * String FAß_NAME {@value #FAß_NAME}
		 * since 28.02.2024
		 */
		private static final String FAß_NAME = "Faß";

		/**
		 * String KARTON_NAME {@value #KARTON_NAME}
		 * since 28.02.2024
		 */
		private static final String KARTON_NAME = "Karton";

		/**
		 * String KISTE_NAME {@value #KISTE_NAME}
		 * since 28.02.2024
		 */
		private static final String KISTE_NAME = "Kiste";

		private static final long serialVersionUID = Verpackung.Persister.class.hashCode();

		public static Verpackung KISTE;
		public static Verpackung KARTON;
		public static Verpackung FASS;
		public static Verpackung UNBEKANNT;
		
		private final VerpackungRepository repository;
		public Persister(VerpackungRepository repository) {
			super(repository);
			this.repository = repository;
			
			if (repository.count() == 0) {
				repository.save(new Verpackung(KISTE_NAME));
				repository.save(new Verpackung(KARTON_NAME));
				repository.save(new Verpackung("Palette"));
				repository.save(new Verpackung("Eimer"));
				repository.save(new Verpackung(FAß_NAME));
				repository.save(new Verpackung(UNBEKANNT_NAME));
			}
			KISTE = repository.findByName(KISTE_NAME).get(0);
			KARTON = repository.findByName(KARTON_NAME).get(0);
			FASS = repository.findByName(FAß_NAME).get(0);
			UNBEKANNT = repository.findByName(UNBEKANNT_NAME).get(0);
		}
		
		public ExtComboBoxProvider<Verpackung> getFormProvider() {
			return new ExtComboBoxProvider<Verpackung>(repository.findAll(), Verpackung::getName);
		}

	}

}
