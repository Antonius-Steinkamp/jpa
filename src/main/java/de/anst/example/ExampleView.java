/**
 * CrusPersonView.java created 07.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 */
package de.anst.example;

/**
 * CrusPersonView created 07.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 *
 */
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import de.anst.MainLayout;
import de.anst.ui.ExtGridCrud;

@PageTitle("Example")
@Route(value = "example", layout = MainLayout.class)
public class ExampleView extends VerticalLayout {

	private static final long serialVersionUID = ExampleView.class.hashCode();
	
	final ExtGridCrud<Example> crud = new ExtGridCrud<Example>(Example.class);
	
    public ExampleView(ExampleRepository repository) {
    	super();

        crud.setCrudListener(new Example.Persister(repository));

        addAndExpand(crud);
    }

}

