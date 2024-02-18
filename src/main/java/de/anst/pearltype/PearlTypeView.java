/**
 * PearlTypeView.java created 09.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 */
package de.anst.pearltype;

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
import de.anst.pearltype.stkelement.StkElement;
import de.anst.ui.ExtGridCrud;
import lombok.extern.java.Log;

@PageTitle("Pearltype")
@Route(value = "pearltype", layout = MainLayout.class)
@Log
public class PearlTypeView extends VerticalLayout {
	
	private static final long serialVersionUID = PearlTypeView.class.hashCode();

	final GridCrud<PearlType> crud = new ExtGridCrud<PearlType>(PearlType.class);

	final Grid<StkElement> childListe = new Grid<StkElement>(StkElement.class);
	private static List<StkElement> emptyList = new ArrayList<StkElement>();
	
    public PearlTypeView(PearlTypeRepository repository) {
    	super();

        crud.setCrudListener(new PearlType.Persister(repository));
        crud.getGrid().setColumns(PearlType.Fields.name, PearlType.Fields.description);

        crud.getGrid().addSelectionListener(e -> {
        	e.getFirstSelectedItem().ifPresentOrElse(p -> childListe.setItems(p.getStkListe()), () -> childListe.setItems(emptyList));
        });
        childListe.setColumns(AUtils.getDeclaredPropertyNamesArray(StkElement.class));

        var childLayout = new VerticalLayout();
        childLayout.add(StkElement.class.getSimpleName());
        childLayout.add(childListe);
        
        var sLayout = new SplitLayout(crud, childLayout);
    	sLayout.setOrientation(Orientation.VERTICAL);
        sLayout.setSizeFull();
        
        addAndExpand(sLayout);
    }


}
