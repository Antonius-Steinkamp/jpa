/**
 * CrusPersonView.java created 07.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 */
package de.anst.vpc.cc;

/**
 * CrusPersonView created 07.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 *
 */
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import de.anst.MainLayout;
import de.anst.ui.ExtGridCrud;
import de.anst.vpc.material.Material;
import de.anst.vpc.material.verpackung.Verpackung;
import de.anst.vpc.segment.bdo.Verbauort;

@PageTitle("Controlcycle")
@Route(value = "cc", layout = MainLayout.class)
public class ControlCycleView extends VerticalLayout {

	private static final long serialVersionUID = ControlCycleView.class.hashCode();

	final ExtGridCrud<ControlCycle> crud = new ExtGridCrud<ControlCycle>(ControlCycle.class);
	
    public ControlCycleView(ControlCycleRepository repository, Material.Persister mPersister, Verbauort.Persister bdoPersister,Verpackung.Persister vP) {
    	super();

        crud.setCrudListener(new ControlCycle.Persister(repository, mPersister, bdoPersister, vP));

        crud.getCrudFormFactory().setVisibleProperties(
        		ControlCycle.Fields.material,
        		ControlCycle.Fields.bdo,
        		ControlCycle.Fields.verpackung,
        		ControlCycle.Fields.behaeltermenge,
        		ControlCycle.Fields.sicherheit,
        		ControlCycle.Fields.anzLager,
        		ControlCycle.Fields.bestand
        		);
        crud.getCrudFormFactory().setFieldProvider(ControlCycle.Fields.material, mPersister.getFormProvider());
        crud.getCrudFormFactory().setFieldProvider(ControlCycle.Fields.bdo, bdoPersister.getFormProvider()); 
        crud.getCrudFormFactory().setFieldProvider(ControlCycle.Fields.verpackung, vP.getFormProvider());


        addAndExpand(crud);
    }


}

