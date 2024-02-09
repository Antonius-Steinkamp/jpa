/**
 * CrusPersonView.java created 07.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 */
package de.anst.material.verpackung;

/**
 * CrusPersonView created 07.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 *
 */
import org.vaadin.crudui.crud.impl.GridCrud;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import de.anst.MainLayout;
import de.anst.ui.ExtGridCrud;

@PageTitle("Verpackung")
@Route(value = "verpackung", layout = MainLayout.class)
public class VerpackungView extends VerticalLayout {
	/**
	 * the long serialVersionUID
	 * since 07.02.2024
	 */
	private static final long serialVersionUID = 6199029108682773392L;
	final GridCrud<Verpackung> crud = new ExtGridCrud<Verpackung>(Verpackung.class);
	
    public VerpackungView(VerpackungRepository repository) {
    	super();

        crud.setCrudListener(new Verpackung.Persister(repository));

        addAndExpand(crud);
    }


}

