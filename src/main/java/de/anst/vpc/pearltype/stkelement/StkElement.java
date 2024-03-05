package de.anst.vpc.pearltype.stkelement;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import de.anst.data.AbstractEntity;
import de.anst.data.JpaCrudService;
import de.anst.vpc.material.Material;
import de.anst.vpc.pearltype.PearlType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

@Entity
@Table(name = "stkelement")
@FieldNameConstants
@NoArgsConstructor
@AllArgsConstructor
public class StkElement extends AbstractEntity {

	@Getter @Setter
    @ManyToOne
    @NotNull
	private Material material;
	
	@Getter @Setter
	@NotNull
	private Double menge;
	
	@Getter @Setter
	@ManyToOne
	@JoinColumn(name="pearl_type_id", nullable=false)
	private PearlType pearltype;

	@Override
	public String toString() {
		return material.getName() + ":" + menge + " " + material.getEinheit().getName();
	}

	
	@Component
	public static class Persister extends JpaCrudService<StkElement, Long,StkElementRepository> {
		private static final long serialVersionUID = StkElement.Persister.class.hashCode();
		
		public Persister(StkElementRepository repository, PearlType.Persister pPearlsType, Material.Persister pMaterial) {
			super(repository);
			
			if (repository.count() == 0) {
				var allMaterials = new ArrayList<Material>(pMaterial.findAll());
				
				for (PearlType pearlType: pPearlsType.findAll()) {
					int i=1;
					for (Material material: allMaterials) {
						var element = new StkElement(material, (double)i++, pearlType );
						repository.save(element);
					}
				}
			}
		}
	}
	
	
}
