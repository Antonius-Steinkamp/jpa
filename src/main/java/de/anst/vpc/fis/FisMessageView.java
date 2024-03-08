/**
 * CrusPersonView.java created 07.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 */
package de.anst.vpc.fis;

/**
 * CrusPersonView created 07.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 *
 */
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import de.anst.MainLayout;
import de.anst.ui.ExtGridCrud;

@PageTitle("FisMeldungen")
@Route(value = "fismeldungen", layout = MainLayout.class)
public class FisMessageView extends VerticalLayout {

	private static final long serialVersionUID = FisMessageView.class.hashCode();

	final ExtGridCrud<FisMessage> crud = new ExtGridCrud<FisMessage>(FisMessage.class);
	
    public FisMessageView(FisMessageRepository repository) {
    	super();

        crud.setCrudListener(new FisMessage.Persister(repository));

        addAndExpand(crud);
    }


}

