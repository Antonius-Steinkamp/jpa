/**
 * CrusPersonView.java created 07.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 */
package de.anst.vpc.segment.meldepunkt;

/**
 * CrusPersonView created 07.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 *
 */
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import de.anst.MainLayout;
import de.anst.ui.ExtGridCrud;
import de.anst.vpc.segment.Segment;

@PageTitle("Meldepunkte")
@Route(value = "meldepunkt", layout = MainLayout.class)
public class MeldepunktView extends VerticalLayout {
	private static final long serialVersionUID = MeldepunktView.class.hashCode();

	final ExtGridCrud<Meldepunkt> crud = new ExtGridCrud<Meldepunkt>(Meldepunkt.class);	
	
    public MeldepunktView(MeldepunktRepository repository, Segment.Persister sPersister) {
    	super();

        crud.setCrudListener(new Meldepunkt.Persister(repository));

        /*
        var vProvider = new ComboBoxProvider<>(StkElement.Fields.material, mPersister.findAll(), new TextRenderer<>(Material::getName), Material::getName);
        crud.getCrudFormFactory().setFieldProvider(StkElement.Fields.material, vProvider);
        */

        crud.getCrudFormFactory().setFieldProvider(Meldepunkt.Fields.segment, sPersister.getFormProvider());

        addAndExpand(crud);
    }


}

