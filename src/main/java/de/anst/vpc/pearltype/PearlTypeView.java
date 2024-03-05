/**
 * PearlTypeView.java created 09.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 */
package de.anst.vpc.pearltype;

import java.util.ArrayList;
import java.util.List;

/**
 * PearlTypeView created 09.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 *
 */
import org.vaadin.crudui.crud.impl.GridCrud;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout.Orientation;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import de.anst.AUtils;
import de.anst.MainLayout;
import de.anst.ui.ExtGridCrud;
import de.anst.vpc.pearltype.stkelement.StkElement;

@PageTitle("Pearltype")
@Route(value = "pearltype", layout = MainLayout.class)
public class PearlTypeView extends VerticalLayout {
	
	private static final long serialVersionUID = PearlTypeView.class.hashCode();

	final GridCrud<PearlType> crud = new ExtGridCrud<PearlType>(PearlType.class);

	final Grid<StkElement> childListe = new Grid<StkElement>(StkElement.class);
	private static final List<StkElement> EMPTY_LIST = new ArrayList<StkElement>();
	
    public PearlTypeView(PearlTypeRepository repository) {
    	super();

        crud.setCrudListener(new PearlType.Persister(repository));
        
        crud.getGrid().setColumns(PearlType.Fields.name, PearlType.Fields.description);

        crud.getGrid().addSelectionListener(e -> {
        	e.getFirstSelectedItem().ifPresentOrElse(p -> childListe.setItems(p.getStkListe()), () -> childListe.setItems(EMPTY_LIST));
        });
        childListe.setColumns(AUtils.getDeclaredPropertyNamesArray(StkElement.class));

        var childLayout = new VerticalLayout();
        childLayout.add("Stückliste");
        childLayout.add(childListe);
        
        var sLayout = new SplitLayout(crud, childLayout);
    	sLayout.setOrientation(Orientation.VERTICAL);
        sLayout.setSizeFull();
        
        addAndExpand(sLayout);
    }


}
