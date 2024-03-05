/**
 * CrusPersonView.java created 07.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 */
package de.anst.vpc.material;

import java.util.ArrayList;
import java.util.List;

/**
 * CrusPersonView created 07.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
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
import de.anst.vpc.material.einheit.Einheit;

@PageTitle("Material (Crud)")
@Route(value = "material", layout = MainLayout.class)
public class MaterialView extends VerticalLayout {
	/**
	 * the long serialVersionUID
	 * since 07.02.2024
	 */
	private static final long serialVersionUID = 6199029108682773392L;
	final GridCrud<Material> crud = new ExtGridCrud<Material>(Material.class);
	
	final Grid<Material> childListe = new Grid<Material>(Material.class);
	
	private static final List<Material> EMPTY_LIST = new ArrayList<Material>();


    public MaterialView(Material.Persister persister, Einheit.Persister eRepo) {
    	super();

        crud.setCrudListener(persister);
        
        crud.getCrudFormFactory().setFieldProvider(Material.Fields.einheit, eRepo.getFormProvider());

        crud.getGrid().addSelectionListener(e -> {
        	e.getFirstSelectedItem().ifPresentOrElse(p -> childListe.setItems(p.getWahlweise()), () -> childListe.setItems(EMPTY_LIST));
        });
        childListe.setColumns(AUtils.getDeclaredPropertyNamesArray(Material.class));

        var childLayout = new VerticalLayout();
        childLayout.add(Material.Fields.wahlweise);
        childLayout.add(childListe);
        
        var sLayout = new SplitLayout(crud, childLayout);
    	sLayout.setOrientation(Orientation.VERTICAL);
        sLayout.setSizeFull();
        
        add(sLayout);
    }


}

