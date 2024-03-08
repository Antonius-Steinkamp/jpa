/**
 * CrusPersonView.java created 07.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 */
package de.anst.about;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.grid.HeaderRow.HeaderCell;
/**
 * CrusPersonView created 07.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 *
 */
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import de.anst.Application;
import de.anst.MainLayout;
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

	/**
	 * String BEANS {@value #BEANS}
	 * since 08.03.2024
	 */
	private static final String BEANS = "Beans";

	private static final long serialVersionUID = EnvView.class.hashCode();

	public EnvView() {
		super();

		final Grid<NameValue> grid = new Grid<NameValue>(NameValue.class);
		HeaderRow headerRow = grid.prependHeaderRow();
		
		final TextField nameFilterField = new TextField();
		nameFilterField.setValueChangeMode(ValueChangeMode.EAGER);
		HeaderCell nameCell = headerRow.getCell(grid.getColumnByKey(NameValue.Fields.name));
		nameCell.setComponent(nameFilterField);

		final TextField valueFilterField = new TextField();
		valueFilterField.setValueChangeMode(ValueChangeMode.EAGER);
		HeaderCell valueCell = headerRow.getCell(grid.getColumnByKey(NameValue.Fields.value));
		valueCell.setComponent(valueFilterField);

		final RadioButtonGroup<String> group = new RadioButtonGroup<>("Quellen");
		
		group.setItems(ENVIRONMENT, PROPERTIES, BEANS);
		group.addValueChangeListener(e -> {
			Collection<NameValue> items;
			if (e.getValue().equals(ENVIRONMENT)) {
				items = getEnvs();
			} else if (e.getValue().equals(PROPERTIES)) {
				items = getProps();
			} else {
				items = getBeans();
			}
			setFilterAndData(grid, items, nameFilterField, valueFilterField);
		});
		group.setValue(ENVIRONMENT);
		add(group);

		setFilterAndData(grid, getEnvs(), nameFilterField, valueFilterField);

		add(grid);
		setSizeFull();
	}

	private static void setFilterAndData(Grid<NameValue> grid, Collection<NameValue> items, TextField filterField, TextField valueFilterField) {
		grid.setItems(items);
		filterField.clear();
		valueFilterField.clear();
		
		final ListDataProvider<NameValue> dataProvider = new ListDataProvider<>(items);
		filterField.addValueChangeListener(e -> {
			dataProvider.setFilter(item -> item.getName().contains(filterField.getValue()));	
			dataProvider.addFilter(item -> item.getValue().contains(valueFilterField.getValue()));	
		});
		valueFilterField.addValueChangeListener(e -> {
			dataProvider.setFilter(item -> item.getValue().contains(valueFilterField.getValue()));	
			dataProvider.addFilter(item -> item.getName().contains(filterField.getValue()));	
		});
		grid.setDataProvider(dataProvider);
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
	
	Collection<NameValue> getBeans() {
		Collection<NameValue> result = new ArrayList<>();
		
		Application.beans.forEach((K,V) -> result.add(new NameValue(K, V)));
		
		return result;
	}

}
