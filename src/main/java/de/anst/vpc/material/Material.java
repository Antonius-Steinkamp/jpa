package de.anst.vpc.material;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import de.anst.AUtils;
import de.anst.data.AbstractEntity;
import de.anst.data.JpaCrudService;
import de.anst.ui.ExtComboBoxProvider;
import de.anst.vpc.material.einheit.Einheit;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
	@OneToMany(fetch = FetchType.EAGER)
	private List<Material> wahlweise = new ArrayList<>();

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
	
	
	public Material(String name, String description, Einheit einheit) {
		this.name = name;
		this.description = description;
		this.einheit = einheit;
	}
	
	@Component
	public static class Persister extends JpaCrudService<Material, Long, MaterialRepository> {

		private static final long serialVersionUID = Material.Persister.class.hashCode();

		private final MaterialRepository repository;

		public Persister(MaterialRepository repository, Einheit.Persister ePersister) {
			super(repository);

			this.repository = repository;
				
			if (repository.count() == 0) {
	
				repository.save(new Material("Unterlegscheibe", "Die Unterlegscheibe", Einheit.Persister.STUECK));
				
				var m4 = new Material("Schraube M4", "Schraube", Einheit.Persister.STUECK);
				var m4s = new Material("Schraube M4S", "Schraube versilbert", Einheit.Persister.STUECK);
				var m4g = new Material("Schraube M4G", "Schraube vergoldet", Einheit.Persister.STUECK);
				repository.save(m4s);
				repository.save(m4g);
				
				m4.getWahlweise().add(m4s);
				m4.getWahlweise().add(m4g);
				
				repository.save(m4);
				
				repository.save(new Material("Schraube M5", "Schraube", Einheit.Persister.STUECK));
				repository.save(new Material("Schraube M5G", "Schraube vergoldet", Einheit.Persister.STUECK));
				repository.save(new Material("Schraube M6", "Schraube", Einheit.Persister.STUECK));
				repository.save(new Material("Schraube M7", "Schraube", Einheit.Persister.STUECK));
				repository.save(new Material("Schraube M8", "Schraube", Einheit.Persister.STUECK));
				
				repository.save(new Material("Öl 10W50", "Öl", Einheit.Persister.LITER));
			}
		}
		
		public ExtComboBoxProvider<Material> getFormProvider() {
			return new ExtComboBoxProvider<Material>(repository.findAll(), Material::toString);
		}

		public Material findByName(final String name) {
			Material result = null;
			List<Material> m = repository.findByName(name);
			if (AUtils.hasValue(m)) {
				result = m.get(0);
			}
			
			return result;
		}
		
	}

}
