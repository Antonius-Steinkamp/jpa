/**
 * CrusPersonView.java created 07.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 */
package de.anst.vpc.pearltype.stkelement;

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
import de.anst.ui.ExtComboBoxProvider;
import de.anst.ui.ExtGridCrud;
import de.anst.vpc.material.Material;
import de.anst.vpc.pearltype.PearlType;

@PageTitle("StkElement")
@Route(value = "stk-element", layout = MainLayout.class)
public class StkElementView extends VerticalLayout {

	private static final long serialVersionUID = StkElementView.class.hashCode();

	final GridCrud<StkElement> crud = new ExtGridCrud<StkElement>(StkElement.class);
	
    public StkElementView(StkElement.Persister repository, Material.Persister mPersister, PearlType.Persister pPersister) {
    	super();

        crud.setCrudListener(repository);

        var vProvider = new ComboBoxProvider<>(StkElement.Fields.material, mPersister.findAll(), new TextRenderer<>(Material::getName), Material::getName);
        crud.getCrudFormFactory().setFieldProvider(StkElement.Fields.material, vProvider);
        crud.getCrudFormFactory().setFieldProvider(StkElement.Fields.pearltype, new ExtComboBoxProvider<PearlType>(pPersister.findAll(), PearlType::getName));


        addAndExpand(crud);
    }


}

