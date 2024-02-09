/**
 * CrusPersonView.java created 07.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 */
package de.anst.material;

/**
 * CrusPersonView created 07.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 *
 */
import org.vaadin.crudui.crud.impl.GridCrud;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import de.anst.MainLayout;
import de.anst.Utils;

@PageTitle("Einheit")
@Route(value = "einheit", layout = MainLayout.class)
public class EinheitView extends VerticalLayout {
	/**
	 * the long serialVersionUID
	 * since 07.02.2024
	 */
	private static final long serialVersionUID = 6199029108682773392L;
	final GridCrud<Einheit> crud = new GridCrud<Einheit>(Einheit.class);
	
    public EinheitView(EinheitRepository repository) {
    	super();

    	String[] declaredPropertyNames = Utils.getDeclaredPropertyNames(Einheit.class);

        crud.setCrudListener(new Einheit.Persister(repository));
        crud.getGrid().setColumns(declaredPropertyNames);
        
        var crudFormFactory = crud.getCrudFormFactory();
        crudFormFactory.setVisibleProperties(declaredPropertyNames);
        crudFormFactory.setUseBeanValidation(true);
        // crudFormFactory.setUseBeanValidation(CrudOperation.ADD, true);
        // crudFormFactory.setUseBeanValidation(CrudOperation.UPDATE, true);
    	addAndExpand(crud);
    }


}

