/**
 * CrusPersonView.java created 07.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 */
package de.anst.i18n;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.vaadin.crudui.form.impl.field.provider.ComboBoxProvider;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import de.anst.MainLayout;
import de.anst.ui.ExtGridCrud;

@PageTitle("Translation")
@Route(value = "stranslation", layout = MainLayout.class)
public class TranslationView extends VerticalLayout {
	/**
	 * the long serialVersionUID
	 * since 07.02.2024
	 */
	private static final long serialVersionUID = 6199029108682773392L;
	final ExtGridCrud<Translation> crud = new ExtGridCrud<Translation>(Translation.class);
	
    public TranslationView(TranslationRepository repository) {
    	super();

    	var persister = new Translation.Persister(repository);
    	
        crud.setCrudListener(persister);
        
        var provider = new ComboBoxProvider<>(Arrays.asList(Translation.Persister.localeNames).stream().map(l -> l.getLanguage()).collect(Collectors.toList()));
        crud.getCrudFormFactory().setFieldProvider(Translation.Fields.locale, provider);

    	addAndExpand(crud);
    }


}

