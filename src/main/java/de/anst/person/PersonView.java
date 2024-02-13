/**
 * CrusPersonView.java created 07.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 */
package de.anst.person;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import de.anst.MainLayout;
import de.anst.ui.ExtendableComboBoxProvider;
import de.anst.ui.ExtGridCrud;

@PageTitle("Persons (Crud)")
@Route(value = "cperson", layout = MainLayout.class)
public class PersonView extends VerticalLayout {
	/**
	 * the long serialVersionUID
	 * since 07.02.2024
	 */
	private static final long serialVersionUID = 6199029108682773392L;
	final ExtGridCrud<Person> crud = new ExtGridCrud<Person>(Person.class);
	
    public PersonView(PersonRepository personRepository) {
    	super();
        crud.setCrudListener(new Person.Persister(personRepository));
        
        crud.getCrudFormFactory().setFieldProvider(Person.Fields.occupation, new ExtendableComboBoxProvider(personRepository.findAllOccupations()));
        crud.getCrudFormFactory().setFieldProvider(Person.Fields.role, new ExtendableComboBoxProvider(personRepository.findAllRoles()));

        addAndExpand(crud);
    }

}

