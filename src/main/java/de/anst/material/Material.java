package de.anst.material;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

import org.springframework.stereotype.Component;

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
public class Material extends AbstractEntity implements Comparable<Material>{

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

	@Override
	public String toString() {
		return name + " / " + einheit.getName();
	}
	
	@Override
	public int compareTo(Material o) {
		if ( o == null ) {
			return 0;
		}
		return this.getName().compareTo(o.getName());
	}
	
	
	public Material(String name, String description, Einheit einheit, Verpackung verpackung) {
		this.name = name;
		this.description = description;
		this.einheit = einheit;
		this.verpackung = verpackung;
	}
	
	@Component
	public static class Persister extends JpaCrudService<Material, Long, MaterialRepository> {

		private static final long serialVersionUID = Material.Persister.class.hashCode();

		private Random random = new Random();
		private final Einheit.Persister einheitenPersister;
		private final Verpackung.Persister verpackungsPersister;

		public Persister(MaterialRepository repository, Einheit.Persister einheitenPersister, Verpackung.Persister verpackungsPersister) {
			super(repository);

				this.einheitenPersister = einheitenPersister;
				this.verpackungsPersister = verpackungsPersister;
				
			if (repository.count() == 0) {
	
				repository.save(new Material("Unterlegscheibe", "Die Unterlegscheibe", getEinheit("stk"), getRandomVerpackung()));
				repository.save(new Material("Schraube M4", "Schraube", getEinheit("stk"), getRandomVerpackung()));
				repository.save(new Material("Schraube M5", "Schraube", getEinheit("stk"), getRandomVerpackung()));
				repository.save(new Material("Schraube M6", "Schraube", getEinheit("stk"), getRandomVerpackung()));
				repository.save(new Material("Schraube M7", "Schraube", getEinheit("stk"), getRandomVerpackung()));
				repository.save(new Material("Schraube M8", "Schraube", getEinheit("stk"), getRandomVerpackung()));
			}
		}
		
		private Einheit getRandomEinheit() {
			var alleEinheiten = new ArrayList<Einheit>( einheitenPersister.findAll());
			return alleEinheiten.get(random.nextInt(alleEinheiten.size()));
			
		}

		private Einheit getEinheit(String name) {
			return new ArrayList<Einheit>( einheitenPersister.findAll()).stream().filter(entity -> name.equals(entity.getName())).findFirst().get();
		}

		private Verpackung getRandomVerpackung() {
			var alleVerpackungen = new ArrayList<Verpackung>( verpackungsPersister.findAll());
			return alleVerpackungen.get(random.nextInt(alleVerpackungen.size()));
		}
	}

}
