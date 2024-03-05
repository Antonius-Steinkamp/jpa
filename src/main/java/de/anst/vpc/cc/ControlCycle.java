/**
 * ControlCycle.java created 27.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 */
package de.anst.vpc.cc;

import org.springframework.stereotype.Component;

import de.anst.data.AbstractEntity;
import de.anst.data.JpaCrudService;
import de.anst.ui.ExtComboBoxProvider;
import de.anst.vpc.material.Material;
import de.anst.vpc.material.verpackung.Verpackung;
import de.anst.vpc.segment.bdo.Verbauort;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import lombok.extern.java.Log;

/**
 * ControlCycle created 27.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 *
 */
@Entity
@Table(name = "cc", uniqueConstraints = {
		@UniqueConstraint (columnNames = {"material", "bdo"}, name="material_bdo"),
		@UniqueConstraint (columnNames = {"name"}, name="name")
		})
@FieldNameConstants
public class ControlCycle extends AbstractEntity implements Comparable<ControlCycle> {
	@NotNull
	@Getter
	private String name;
	
	private void setName() {
		if (material != null && bdo != null) {
			name = "R"+ material.getId() + "@" + bdo.getId();
		}
	}
	
	public String toString() {
		return material.getName() + "/" + bdo.getName();
	}
	
	@Getter
	@NotNull
	@ManyToOne
	private Material material;
	public void setMaterial(Material material) {
		this.material = material;
		setName();
	}
	
	@Getter
	@NotNull
	@ManyToOne
	private Verbauort bdo;
	public void setBdo(Verbauort bdoNeu) {
		bdo = bdoNeu;
		setName();
	}
	
	@Getter @Setter
	private long anzLager = -1; // so viele Kisten können hier hin
	
	
	@Getter @Setter
    @ManyToOne
    @NotNull
	private Verpackung verpackung;
	
	@Getter @Setter
	private Double bestand = Double.valueOf(0);

	@Getter @Setter
	@Positive
	private Double behaeltermenge = Double.valueOf(1);

	@Getter @Setter
	private Double sicherheit = Double.valueOf(0);

	@Override
	public int compareTo(ControlCycle o) {
		return material.compareTo(o.getMaterial());
	}
	
	@Component
	@Log
	public static class Persister extends JpaCrudService<ControlCycle, Long,ControlCycleRepository> {
		private static final long serialVersionUID = ControlCycle.Persister.class.hashCode();
		
		private final ControlCycleRepository repository;
		
		private final Material.Persister mPersister;
		private final Verbauort.Persister bdoPersister;
		
		public Persister(ControlCycleRepository repository, Material.Persister mPersister, Verbauort.Persister bdoPersister) {
			super(repository);
			this.repository = repository;
			this.mPersister = mPersister;
			this.bdoPersister = bdoPersister;
			
			if (repository.count() == 0) {
				var cc = new ControlCycle();
				cc.setMaterial(mPersister.findByName("Schraube M4"));
				cc.setBdo(bdoPersister.findByName("D1000"));
				cc.setVerpackung(Verpackung.Persister.KISTE);
		
				log.info("CCName = " + cc.getName());
				
				
				repository.save(createOf("Schraube M4", "D1000"));
				repository.save(createOf("Schraube M5", "D1001"));
				repository.save(createOf("Schraube M6", "D1002"));
				repository.save(createOf("Schraube M7", "D1003"));
				// Generate Data (if necessary)
			}
		}
		
		public ExtComboBoxProvider<ControlCycle> getFormProvider() {
			return new ExtComboBoxProvider<ControlCycle>(repository.findAll(), ControlCycle::getName);
		}
		
		private ControlCycle createOf(String materialName, String bdoName) {
			ControlCycle result = new ControlCycle();
			result.setMaterial(mPersister.findByName(materialName));
			result.setBdo(bdoPersister.findByName(bdoName));
			result.setVerpackung(Verpackung.Persister.KISTE);
			
			return result;
		}

	}
	
	
}
