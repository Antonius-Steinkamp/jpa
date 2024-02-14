/**
 * CrusPersonView.java created 07.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 */
package de.anst.material.stkelement;

/**
 * CrusPersonView created 07.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 *
 */
import org.vaadin.crudui.crud.impl.GridCrud;
import org.vaadin.crudui.form.impl.field.provider.ComboBoxProvider;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.TextRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import de.anst.MainLayout;
import de.anst.material.Material;
import de.anst.material.verpackung.Verpackung;
import de.anst.pearl.type.PearlType;
import de.anst.ui.ExtGridCrud;

@PageTitle("StkElement")
@Route(value = "stk-element", layout = MainLayout.class)
public class StkElementView extends VerticalLayout {

	private static final long serialVersionUID = StkElementView.class.hashCode();

	final GridCrud<StkElement> crud = new ExtGridCrud<StkElement>(StkElement.class);
	
    public StkElementView(StkElementRepository repository, Material.Persister mPersister, PearlType.Persister pPersister) {
    	super();

        crud.setCrudListener(new StkElement.Persister(repository));

        var vProvider = new ComboBoxProvider<>(StkElement.Fields.material, mPersister.findAll(), new TextRenderer<>(Material::getName), Material::getName);
        crud.getCrudFormFactory().setFieldProvider(StkElement.Fields.material, vProvider);

        var pProvider = new ComboBoxProvider<>(StkElement.Fields.pearltype, pPersister.findAll(), new TextRenderer<>(PearlType::getName), PearlType::getName);
        crud.getCrudFormFactory().setFieldProvider(StkElement.Fields.pearltype, pProvider);


        addAndExpand(crud);
    }


}

