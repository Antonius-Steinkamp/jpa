/**
 * StkView.java created 05.03.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 */
package de.anst.vpc.pearl.stk;

import org.vaadin.crudui.crud.impl.GridCrud;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import de.anst.MainLayout;
import de.anst.ui.ExtGridCrud;
import de.anst.vpc.cc.ControlCycle;
import de.anst.vpc.pearl.Pearl;

/**
 * StkView created 05.03.2024 by
 * <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 *
 */
@PageTitle("Verbau")
@Route(value = "verbau", layout = MainLayout.class)
public class StkView extends VerticalLayout {
	private static final long serialVersionUID = StkView.class.hashCode();

	final GridCrud<Stk> crud = new ExtGridCrud<Stk>(Stk.class);

	public StkView(StkRepository repository, Pearl.Persister pPersister, ControlCycle.Persister cPersister) {
		super();

		crud.setCrudListener(new Stk.Persister(repository));

        crud.getCrudFormFactory().setFieldProvider(Stk.Fields.pearl, pPersister.getFormProvider());
        crud.getCrudFormFactory().setFieldProvider(Stk.Fields.cc, cPersister.getFormProvider());

		addAndExpand(crud);
	}

}