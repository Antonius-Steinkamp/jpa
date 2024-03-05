package de.anst.vpc.material.einheit;

import org.springframework.stereotype.Component;

import de.anst.data.AbstractEntity;
import de.anst.data.JpaCrudService;
import de.anst.ui.ExtComboBoxProvider;
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
@Table(name = "einheit")
@FieldNameConstants
@AllArgsConstructor
@NoArgsConstructor
public class Einheit extends AbstractEntity implements Comparable<Einheit>{

	@Getter @Setter
	@NotNull
	@Column(unique = true)
    private String name;
	
	@Getter @Setter
    private String description; 

	@Override
	public String toString() {
		return name;
	}
	@Override
	public int compareTo(Einheit o) {
		return getName().compareTo(o.getName());
	}

	
	@Component
	@Log
	public static class Persister extends JpaCrudService<Einheit, Long, EinheitRepository> {
		/**
		 * String L_STRING {@value #L_STRING}
		 * since 28.02.2024
		 */
		private static final String L_STRING = "l";

		/**
		 * String KG_STRING {@value #KG_STRING}
		 * since 28.02.2024
		 */
		private static final String KG_STRING = "kg";

		/**
		 * String STK_STRING {@value #STK_STRING}
		 * since 28.02.2024
		 */
		private static final String STK_STRING = "stk";

		/**
		 * the long serialVersionUID
		 * since 09.02.2024
		 */
		private static final long serialVersionUID = 868432046536992979L;
		
		private final EinheitRepository repository;
		
		public static Einheit STUECK;
		public static Einheit KILO;
		public static Einheit LITER;
		
		public Persister(EinheitRepository repository) {
			super(repository);
			this.repository = repository;
			
			log.info("**** " + this.getClass().getName() );
			if (repository.count() == 0) {
				STUECK = repository.save(new Einheit(STK_STRING, "Stück"));
				log.info("STUECK ist " + STUECK.toString());
				KILO = repository.save(new Einheit(KG_STRING, "Kilogramm"));
				LITER = repository.save(new Einheit(L_STRING, "Liter"));
				repository.save(new Einheit("ml", "Milli-Liter"));
				repository.save(new Einheit("m2", "Quadratmeter"));
			}
			
			STUECK = repository.findByName(STK_STRING).get(0);
			KILO = repository.findByName(KG_STRING).get(0);
			LITER = repository.findByName(L_STRING).get(0);
			
			log.info(this.getClass().getName() + " has " + repository.count() + " entries");
			
		}
	
		public ExtComboBoxProvider<Einheit> getFormProvider() {
			return new ExtComboBoxProvider<Einheit>(repository.findAll(), Einheit::getName);
		}

	}

}
