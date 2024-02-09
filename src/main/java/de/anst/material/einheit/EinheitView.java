/**
 * CrusPersonView.java created 07.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 */
package de.anst.material.einheit;

/**
 * CrusPersonView created 07.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 *
 */
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import de.anst.MainLayout;
import de.anst.ui.ExtGridCrud;

@PageTitle("Einheit")
@Route(value = "einheit", layout = MainLayout.class)
public class EinheitView extends VerticalLayout {
	/**
	 * the long serialVersionUID
	 * since 07.02.2024
	 */
	private static final long serialVersionUID = 6199029108682773392L;
	final ExtGridCrud<Einheit> crud = new ExtGridCrud<Einheit>(Einheit.class);
	
    public EinheitView(EinheitRepository repository) {
    	super();

        crud.setCrudListener(new Einheit.Persister(repository));

        addAndExpand(crud);
    }


}

