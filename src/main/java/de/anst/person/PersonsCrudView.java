/**
 * CrusPersonView.java created 07.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 */
package de.anst.person;

import java.lang.reflect.Field;
import java.util.ArrayList;

import org.vaadin.crudui.crud.CrudOperation;
/**
 * CrusPersonView created 07.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 *
 */
import org.vaadin.crudui.crud.impl.GridCrud;
import org.vaadin.crudui.form.CrudFormFactory;
import org.vaadin.crudui.layout.CrudLayout;

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

    	String[] declaredPropertyNames = getDeclaredPropertyNames(Person.class);

        crud.setCrudListener(new Person.Persister(personRepository));
        crud.getGrid().setColumns(declaredPropertyNames);
        
        CrudFormFactory<Person> crudFormFactory = crud.getCrudFormFactory();
        crudFormFactory.setVisibleProperties(declaredPropertyNames);
        crudFormFactory.setUseBeanValidation(CrudOperation.ADD, true);
        crudFormFactory.setUseBeanValidation(CrudOperation.UPDATE, true);
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

