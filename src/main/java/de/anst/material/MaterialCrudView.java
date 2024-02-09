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
import de.anst.Utils;

@PageTitle("Material (Crud)")
@Route(value = "material", layout = MainLayout.class)
public class MaterialCrudView extends VerticalLayout {
	/**
	 * the long serialVersionUID
	 * since 07.02.2024
	 */
	private static final long serialVersionUID = 6199029108682773392L;
	final GridCrud<Material> crud = new GridCrud<Material>(Material.class);
	
    public MaterialCrudView(MaterialRepository repository, EinheitRepository eRepo) {
    	super();

    	String[] declaredPropertyNames = Utils.getDeclaredPropertyNames(Material.class);

        crud.setCrudListener(new Material.Persister(repository, eRepo));
        crud.getGrid().setColumns(declaredPropertyNames);
        
        CrudFormFactory<Material> crudFormFactory = crud.getCrudFormFactory();
        crudFormFactory.setVisibleProperties(declaredPropertyNames);
        crudFormFactory.setUseBeanValidation(true);
        // crudFormFactory.setUseBeanValidation(CrudOperation.ADD, true);
        // crudFormFactory.setUseBeanValidation(CrudOperation.UPDATE, true);
        
        crud.getCrudFormFactory().setFieldProvider(Material.Fields.einheit, new ComboBoxProvider<>(Material.Fields.einheit, eRepo.findAll(), new TextRenderer<>(Einheit::getName), Einheit::getName));


    	addAndExpand(crud);
    }


}

