package de.anst.material;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

import de.anst.data.AbstractEntity;
import de.anst.data.JpaCrudService;
import de.anst.material.einheit.Einheit;
import de.anst.material.einheit.EinheitRepository;
import de.anst.material.verpackung.Verpackung;
import de.anst.material.verpackung.VerpackungRepository;
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
	@NotNull
	private Double gewicht = Double.valueOf(0);;
	
	@Getter @Setter
    @ManyToOne
    @NotNull
	private Einheit einheit;

	@Getter @Setter
    @ManyToOne
    @NotNull
	private Verpackung verpackung;

	public Material(String name, String description, Einheit einheit, Verpackung verpackung) {
		this.name = name;
		this.description = description;
		this.einheit = einheit;
		this.verpackung = verpackung;
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

		public Persister(MaterialRepository repository, EinheitRepository eRepo, VerpackungRepository vRepo) {
			super(repository);
			if (repository.count() == 0) {
				Random random = new Random();

				var einheitenPersister = new Einheit.Persister(eRepo);
				var alleEinheiten = new ArrayList<Einheit>( einheitenPersister.findAll());
				Einheit einheit = alleEinheiten.get(random.nextInt(alleEinheiten.size()));
				
				var verpackungsPersister = new Verpackung.Persister(vRepo);
				var alleVerpackungen = new ArrayList<Verpackung>( verpackungsPersister.findAll());
				Verpackung verpackung = alleVerpackungen.get(random.nextInt(alleVerpackungen.size()));
				
				repository.save(new Material("Unterlegscheibe", "Die Unterlegscheibe", einheit, verpackung));
			}
		}
	}
}
