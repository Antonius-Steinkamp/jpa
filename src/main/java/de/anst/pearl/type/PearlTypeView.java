/**
 * PearlTypeView.java created 09.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 */
package de.anst.pearl.type;

/**
 * PearlTypeView created 09.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 *
 */
import org.vaadin.crudui.crud.impl.GridCrud;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import de.anst.MainLayout;
import de.anst.ui.ExtGridCrud;

@PageTitle("Pearltype")
@Route(value = "pearltype", layout = MainLayout.class)
public class PearlTypeView extends VerticalLayout {
	/**
	 * the long serialVersionUID
	 * since 07.02.2024
	 */
	private static final long serialVersionUID = 6199029108682773392L;
	final GridCrud<PearlType> crud = new ExtGridCrud<PearlType>(PearlType.class);
	
    public PearlTypeView(PearlTypeRepository repository) {
    	super();

        crud.setCrudListener(new PearlType.Persister(repository));

        addAndExpand(crud);
    }


}
