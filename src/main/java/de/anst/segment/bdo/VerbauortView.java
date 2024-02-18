/**
 * VerbauortView.java created 18.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 */
package de.anst.segment.bdo;

/**
 * VerbauortView created 18.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 *
 */
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import de.anst.MainLayout;
import de.anst.segment.Segment;
import de.anst.segment.meldepunkt.Meldepunkt;
import de.anst.ui.ExtGridCrud;

@PageTitle("Verbauorte")
@Route(value = "Verbauortmeldepunkt", layout = MainLayout.class)
public class VerbauortView extends VerticalLayout {
	private static final long serialVersionUID = VerbauortView.class.hashCode();
	
	final ExtGridCrud<Verbauort> crud = new ExtGridCrud<Verbauort>(Verbauort.class);	
	public VerbauortView(VerbauortRepository repository, Segment.Persister sPersister, Meldepunkt.Persister pPersister) {
		super();
		
		crud.setCrudListener(new Verbauort.Persister(repository, pPersister));

        crud.getCrudFormFactory().setFieldProvider(Verbauort.Fields.mp, pPersister.getProvider());

		addAndExpand(crud);
	}
	
	
}
