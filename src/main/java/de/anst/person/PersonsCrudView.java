/**
 * CrusPersonView.java created 07.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 */
package de.anst.person;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import de.anst.MainLayout;
import de.anst.ui.ExtGridCrud;

@PageTitle("Persons (Crud)")
@Route(value = "cperson", layout = MainLayout.class)
public class PersonsCrudView extends VerticalLayout {
	/**
	 * the long serialVersionUID
	 * since 07.02.2024
	 */
	private static final long serialVersionUID = 6199029108682773392L;
	final ExtGridCrud<Person> crud = new ExtGridCrud<Person>(Person.class);
	
    public PersonsCrudView(PersonRepository personRepository) {
    	super();
        crud.setCrudListener(new Person.Persister(personRepository));

        addAndExpand(crud);
    }

}

