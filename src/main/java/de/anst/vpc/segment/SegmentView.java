/**
 * CrusPersonView.java created 07.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 */
package de.anst.vpc.segment;

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

import de.anst.MainLayout;
import de.anst.ui.ExtGridCrud;
import de.anst.vpc.pearl.Pearl;
import de.anst.vpc.segment.meldepunkt.Meldepunkt;

@PageTitle("Segments")
@Route(value = "segment", layout = MainLayout.class)
public class SegmentView extends VerticalLayout {
	private static final long serialVersionUID = SegmentView.class.hashCode();

	final ExtGridCrud<Segment> crud = new ExtGridCrud<Segment>(Segment.class);
	
	final Grid<Meldepunkt> mpChain = new Grid<Meldepunkt>(Meldepunkt.class);
	final Grid<Pearl> pearlChain = new Grid<Pearl>(Pearl.class);
	
	private static List<Meldepunkt> emptyMpList = new ArrayList<Meldepunkt>();
	private static List<Pearl> emptyPearlChain = new ArrayList<Pearl>();

    public SegmentView(SegmentRepository repository) {
    	super();

        crud.setCrudListener(new Segment.Persister(repository));

        crud.getGrid().setColumns(Segment.Fields.name, Segment.Fields.description, Segment.Fields.taktZeit, Segment.Fields.maxPos);

        crud.getGrid().addSelectionListener(e -> {
        	e.getFirstSelectedItem().ifPresentOrElse(
        		p -> {
        			mpChain.setItems(p.getMeldepunkte()); 
        			pearlChain.setItems(p.getPearlChain());
        			}, 
        		() -> {
        			mpChain.setItems(emptyMpList); 
        			pearlChain.setItems(emptyPearlChain);
        		});
        });
        
        mpChain.setColumns(Meldepunkt.Fields.name, Meldepunkt.Fields.description, Meldepunkt.Fields.taktzeit, Meldepunkt.Fields.eigenschaften);
        pearlChain.setColumns(Pearl.Fields.name, Pearl.Fields.type, Pearl.Fields.pos);
        

        VerticalLayout childLayout = new VerticalLayout();
        childLayout.add(Meldepunkt.class.getSimpleName());
        childLayout.add(mpChain);
        childLayout.add("Perlenkette");
        childLayout.add(pearlChain);
        
        var sLayout = new SplitLayout(crud, childLayout);
    	sLayout.setOrientation(Orientation.VERTICAL);
        sLayout.setSizeFull();
        
        addAndExpand(sLayout);
    }


}

