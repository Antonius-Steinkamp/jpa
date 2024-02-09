package de.anst.material;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import de.anst.data.AbstractEntity;
import de.anst.data.JpaCrudService;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

@Entity
@Table(name = "material")
@FieldNameConstants
@AllArgsConstructor
@NoArgsConstructor
public class Material extends AbstractEntity {

	@Getter @Setter
	@NotNull
	@Column(unique = true)
    private String name;
	
	@Getter @Setter
    private String description;
	
	@Getter @Setter
    @ManyToOne
	private Einheit einheit;
	
	public Material(String name, String description, Einheit einheit) {
		this.name = name;
		this.description = description;
		this.einheit = einheit;
	}
	
	@Getter
	private LocalDateTime cdate = LocalDateTime.now();
	
	@Getter
	private LocalDateTime udate = LocalDateTime.now();

	@PreUpdate
	private void preupdate() {
		udate = LocalDateTime.now();
	}
	
	public static class Persister extends JpaCrudService<Material, Long, MaterialRepository> {

		/**
		 * the long serialVersionUID
		 * since 09.02.2024
		 */
		private static final long serialVersionUID = 868432046536992979L;

		public Persister(MaterialRepository repository, EinheitRepository eRepo) {
			super(repository);
			if (repository.count() == 0) {
				var persister = new Einheit.Persister(eRepo);
				Random random = new Random();
				var alleEinheiten = new ArrayList<Einheit>( persister.findAll());
				Einheit einheit = alleEinheiten.get(random.nextInt(alleEinheiten.size()));
				repository.save(new Material("Unterlegscheibe", "Die Unterlegscheibe", einheit));
			}
		}
	}
}
