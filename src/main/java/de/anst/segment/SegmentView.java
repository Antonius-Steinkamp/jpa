/**
 * CrusPersonView.java created 07.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 */
package de.anst.segment;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.grid.Grid;
/**
 * CrusPersonView created 07.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 *
 */
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout.Orientation;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import de.anst.AUtils;
import de.anst.MainLayout;
import de.anst.segment.meldepunkt.Meldepunkt;
import de.anst.ui.ExtGridCrud;

@PageTitle("Segments")
@Route(value = "segment", layout = MainLayout.class)
public class SegmentView extends VerticalLayout {
	private static final long serialVersionUID = SegmentView.class.hashCode();

	final ExtGridCrud<Segment> crud = new ExtGridCrud<Segment>(Segment.class);
	
	final Grid<Meldepunkt> childListe = new Grid<Meldepunkt>(Meldepunkt.class);
	private static List<Meldepunkt> emptyList = new ArrayList<Meldepunkt>();

    public SegmentView(SegmentRepository repository) {
    	super();

        crud.setCrudListener(new Segment.Persister(repository));

        crud.getGrid().setColumns(Segment.Fields.name, Segment.Fields.description, Segment.Fields.taktZeit);

        crud.getGrid().addSelectionListener(e -> {
        	e.getFirstSelectedItem().ifPresentOrElse(p -> childListe.setItems(p.getMeldepunkte()), () -> childListe.setItems(emptyList));
        });
        childListe.setColumns(AUtils.getDeclaredPropertyNamesArray(Meldepunkt.class));

        VerticalLayout childLayout = new VerticalLayout();
        childLayout.add(Meldepunkt.class.getSimpleName());
        childLayout.add(childListe);
        var sLayout = new SplitLayout(crud, childLayout);
    	sLayout.setOrientation(Orientation.VERTICAL);
        sLayout.setSizeFull();
        
        addAndExpand(sLayout);
    }


}

