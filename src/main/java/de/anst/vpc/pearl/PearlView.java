/**
 * StkView.java created 05.03.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 */
package de.anst.vpc.pearl;

import org.vaadin.crudui.crud.impl.GridCrud;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import de.anst.MainLayout;
import de.anst.ui.ExtGridCrud;
import de.anst.vpc.pearltype.PearlType;
import de.anst.vpc.segment.Segment;

/**
 * StkView created 05.03.2024 by
 * <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 *
 */
@PageTitle("Perlen")
@Route(value = "perlen", layout = MainLayout.class)
public class PearlView extends VerticalLayout {
	private static final long serialVersionUID = PearlView.class.hashCode();

	final GridCrud<Pearl> crud = new ExtGridCrud<Pearl>(Pearl.class);

	public PearlView(PearlRepository repository, Segment.Persister sPersister, PearlType.Persister pPersister) {
		super();

		crud.setCrudListener(new Pearl.Persister(repository));
		
        crud.getCrudFormFactory().setFieldProvider(Pearl.Fields.segment, sPersister.getFormProvider());
        crud.getCrudFormFactory().setFieldProvider(Pearl.Fields.type, pPersister.getFormProvider());


		addAndExpand(crud);
	}

}