/**
 * PearlTypeView.java created 09.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 */
package de.anst.parameter;

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

@PageTitle("Parameter")
@Route(value = "parameter", layout = MainLayout.class)
public class ParameterView extends VerticalLayout {

	final GridCrud<Parameter> crud = new ExtGridCrud<Parameter>(Parameter.class);
	
    public ParameterView(ParameterRepository repository) {
    	super();

        crud.setCrudListener(new Parameter.Persister(repository));

        addAndExpand(crud);
    }


}
