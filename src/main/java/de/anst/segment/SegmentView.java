/**
 * CrusPersonView.java created 07.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 */
package de.anst.segment;

/**
 * CrusPersonView created 07.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 *
 */
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import de.anst.MainLayout;
import de.anst.ui.ExtGridCrud;

@PageTitle("Segments")
@Route(value = "segment", layout = MainLayout.class)
public class SegmentView extends VerticalLayout {
	private static final long serialVersionUID = SegmentView.class.hashCode();

	final ExtGridCrud<Segment> crud = new ExtGridCrud<Segment>(Segment.class);
	
    public SegmentView(SegmentRepository repository) {
    	super();

        crud.setCrudListener(new Segment.Persister(repository));

        addAndExpand(crud);
    }


}

