/**
 * CrusPersonView.java created 07.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 */
package de.anst.about;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.vaadin.flow.component.grid.Grid;
/**
 * CrusPersonView created 07.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 *
 */
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import de.anst.AUtils;
import de.anst.MainLayout;
import de.anst.ui.ExtGridCrud;
import lombok.extern.java.Log;

@PageTitle("Ênv")
@Route(value = "env", layout = MainLayout.class)
@Log
public class EnvView extends VerticalLayout {

	/**
	 * String PROPERTIES {@value #PROPERTIES}
	 * since 16.02.2024
	 */
	private static final String PROPERTIES = "Properties";

	/**
	 * String ENVIRONMENT {@value #ENVIRONMENT}
	 * since 16.02.2024
	 */
	private static final String ENVIRONMENT = "Environment";

	private static final long serialVersionUID = EnvView.class.hashCode();

	final Grid<NameValue> grid = new Grid<NameValue>(NameValue.class);

	public EnvView() {
		super();

		RadioButtonGroup<String> group = new RadioButtonGroup<>("Quellen");
		group.setItems(ENVIRONMENT, PROPERTIES);
		group.addValueChangeListener(e -> {
			if (e.getValue().equals(ENVIRONMENT)) {
				grid.setItems(getEnvs());
			} else {
				grid.setItems(getProps());
			}
		});

		grid.setItems(getEnvs());
		group.setValue(ENVIRONMENT);


		add(group, grid);
		setSizeFull();
	}

	Collection<NameValue> getEnvs() {
		Collection<NameValue> result = new ArrayList<>();

		System.getenv().forEach((key, value) -> result.add(new NameValue(key, value)));

		return result;
	}

	Collection<NameValue> getProps() {
		Collection<NameValue> result = new ArrayList<>();

		Properties properties = System.getProperties();
		properties.keySet().forEach(key -> result.add(new NameValue(key.toString(), properties.get(key).toString())));

		return result;
	}

}
