/**
 * CrusPersonView.java created 07.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 */
package de.anst.material;

/**
 * CrusPersonView created 07.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 *
 */
import org.vaadin.crudui.crud.impl.GridCrud;
import org.vaadin.crudui.form.CrudFormFactory;
import org.vaadin.crudui.form.impl.field.provider.ComboBoxProvider;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.TextRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import de.anst.MainLayout;
import de.anst.AUtils;
import de.anst.material.einheit.Einheit;
import de.anst.material.einheit.EinheitRepository;
import de.anst.material.verpackung.Verpackung;
import de.anst.material.verpackung.VerpackungRepository;
import de.anst.ui.ExtComboBoxProvider;
import de.anst.ui.ExtGridCrud;

@PageTitle("Material (Crud)")
@Route(value = "material", layout = MainLayout.class)
public class MaterialView extends VerticalLayout {
	/**
	 * the long serialVersionUID
	 * since 07.02.2024
	 */
	private static final long serialVersionUID = 6199029108682773392L;
	final GridCrud<Material> crud = new ExtGridCrud<Material>(Material.class);
	
    public MaterialView(Material.Persister persister, Einheit.Persister eRepo, Verpackung.Persister vRepo) {
    	super();

        crud.setCrudListener(persister);
        
        var provider = new ExtComboBoxProvider<>(eRepo.findAll(), Einheit::getName);
        crud.getCrudFormFactory().setFieldProvider(Material.Fields.einheit, provider);

        var vProvider = new ComboBoxProvider<>(Material.Fields.verpackung, vRepo.findAll(), new TextRenderer<>(Verpackung::getName), Verpackung::getName);
        crud.getCrudFormFactory().setFieldProvider(Material.Fields.verpackung, vProvider);


    	addAndExpand(crud);
    }


}

