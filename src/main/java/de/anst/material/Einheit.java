package de.anst.material;

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

@Entity
@Table(name = "einheit")
@FieldNameConstants
@AllArgsConstructor
@NoArgsConstructor
public class Einheit extends AbstractEntity {

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

	@Component
	public static class Persister extends JpaCrudService<Einheit, Long, EinheitRepository> {
		/**
		 * the long serialVersionUID
		 * since 09.02.2024
		 */
		private static final long serialVersionUID = 868432046536992979L;

		public Persister(EinheitRepository repository) {
			super(repository);
			if (repository.count() == 0) {
				repository.save(new Einheit("stk", "Stück"));
				repository.save(new Einheit("kg", "Kilogramm"));
				repository.save(new Einheit("l", "Liter"));
				repository.save(new Einheit("ml", "Milli-Liter"));
				repository.save(new Einheit("m2", "Quadratmeter"));
			}
		}
	}
}
