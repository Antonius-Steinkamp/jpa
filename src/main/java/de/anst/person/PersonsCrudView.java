/**
 * CrusPersonView.java created 07.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 */
package de.anst.person;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * CrusPersonView created 07.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 *
 */
import org.vaadin.crudui.crud.impl.GridCrud;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import de.anst.MainLayout;

@PageTitle("Persons (Crud)")
@Route(value = "cperson", layout = MainLayout.class)
public class PersonsCrudView extends VerticalLayout {
	/**
	 * the long serialVersionUID
	 * since 07.02.2024
	 */
	private static final long serialVersionUID = 6199029108682773392L;
	final GridCrud<Person> crud = new GridCrud<Person>(Person.class);
	
    public PersonsCrudView(PersonRepository personRepository) {
    	super();


        crud.setCrudListener(new Person.CPersister(personRepository));
        crud.getGrid().setColumns(getDeclaredPropertyNames(Person.class));
    	addAndExpand(crud);
    }

	/**
	 * @param clasz
	 * @return
	 * since 07.02.2024
	 */
	private String[] getDeclaredPropertyNames(Class<?> clasz) {
		var fieldList = new ArrayList<>();
		for( Field field: clasz.getDeclaredFields()) {
			fieldList.add(field.getName());
		}
		return (String[]) fieldList.toArray(new String[fieldList.size()]);
	}
}

