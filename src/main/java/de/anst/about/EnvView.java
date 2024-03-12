/**
 * CrusPersonView.java created 07.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 */
package de.anst.about;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.function.BiPredicate;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.grid.HeaderRow.HeaderCell;
import com.vaadin.flow.component.grid.dataview.GridListDataView;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
/**
 * CrusPersonView created 07.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 *
 */
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.function.SerializablePredicate;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import de.anst.AUtils;
import de.anst.Application;
import de.anst.MainLayout;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.java.Log;

@PageTitle("Ênv")
@Route(value = "env", layout = MainLayout.class)
@Log
public class EnvView extends VerticalLayout {

	/**
	 * String PROPERTIES {@value #PROPERTIES} since 16.02.2024
	 */
	private static final String PROPERTIES = "Properties";

	/**
	 * String ENVIRONMENT {@value #ENVIRONMENT} since 16.02.2024
	 */
	private static final String ENVIRONMENT = "Environment";

	/**
	 * String BEANS {@value #BEANS} since 08.03.2024
	 */
	private static final String BEANS = "Beans";

	private static final long serialVersionUID = EnvView.class.hashCode();

	public EnvView() {
		super();

		log.info("ctor");

		NameValueFilter filter = new NameValueFilter();
		
		final Grid<NameValue> grid = new Grid<NameValue>(NameValue.class);
		grid.getHeaderRows().clear();
		HeaderRow headerRow = grid.appendHeaderRow();

		HeaderCell nameCell = headerRow.getCell(grid.getColumnByKey(NameValue.Fields.name));
		nameCell.setComponent(filter.getNameFilterField());

		HeaderCell valueCell = headerRow.getCell(grid.getColumnByKey(NameValue.Fields.value));
		valueCell.setComponent(filter.getValueFilterField());

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
			setFilterAndData(grid, items, filter);
		});
		group.setValue(ENVIRONMENT);
		add(group);

		setFilterAndData(grid, getEnvs(), filter);

		add(grid);
		setSizeFull();
	}

	private static class TextFilterField extends HorizontalLayout {
		private static final long serialVersionUID = EnvView.TextFilterField.class.hashCode();

		@Getter
		private TextField textField;
		
		@Getter
		private ComboBox<TextPredicate> vergleich;
		
		public TextFilterField() {
			textField = new TextField();
			textField.setValueChangeMode(ValueChangeMode.EAGER);
			textField.setClearButtonVisible(true);
			textField.addThemeVariants(TextFieldVariant.LUMO_SMALL);
			textField.setWidthFull();
			
			vergleich = new ComboBox<TextPredicate>();
			vergleich.setItems(TextPredicate.getPredicated());
			vergleich.setValue(TextPredicate.getPredicated().get(0));
			vergleich.setItemLabelGenerator(TextPredicate::getName);

			HorizontalLayout layout = new HorizontalLayout();
			layout.add(textField, vergleich);
	
			add(layout);
			
			layout.setWidthFull();
			layout.getStyle().set("max-width", "100%");
		}
		
		public String getText() {
			return textField.getValue();
		}
	}
	
	@AllArgsConstructor
	private static class TextPredicate {
		@Getter
		private BiPredicate<String, String> vergleich;
		@Getter
		private String name;
		
		private static List<TextPredicate> textPredicates = new ArrayList<>();
		
		private static final BiPredicate<String, String> EQUALS = (s1, s2) -> s1 != null && s2 != null && s1.equals(s2);
		private static final BiPredicate<String, String> EQUALS_IC = (s1, s2) -> s1 != null && s2 != null && s1.toUpperCase().equals(s2.toUpperCase());
		private static final BiPredicate<String, String> CONTAINS = (s1, s2) -> s1 != null && s2 != null && s1.contains(s2);
		private static final BiPredicate<String, String> CONTAINS_IC = (s1, s2) -> s1 != null && s2 != null && s1.toUpperCase().contains(s2.toUpperCase());
		private static final BiPredicate<String, String> BEGINS_WITH = (s1, s2) -> s1 != null && s2 != null && s1.startsWith(s2);
		private static final BiPredicate<String, String> ENDS_WITH = (s1, s2) -> s1 != null && s2 != null && s1.endsWith(s2);
		
		public static List<TextPredicate> getPredicated() {
			if (!AUtils.hasValue(textPredicates)) {
				textPredicates.add(new TextPredicate(CONTAINS_IC, "Cont"));
				textPredicates.add(new TextPredicate(EQUALS, "equals"));
				textPredicates.add(new TextPredicate(EQUALS_IC, "Equals"));
				textPredicates.add(new TextPredicate(CONTAINS, "cont"));
				textPredicates.add(new TextPredicate(BEGINS_WITH, "xxx%"));
				textPredicates.add(new TextPredicate(ENDS_WITH, "%xxx"));
			}
			
			return textPredicates;
		}
	}
	
	
	static String uppercase(String s) {
		if (AUtils.hasValue(s)) {
			return s.toUpperCase();
		}
		
		return s;
	}
	
	
	private static class NameValueFilter implements SerializablePredicate<NameValue> {
		private static final long serialVersionUID = EnvView.NameValueFilter.class.hashCode();

		@Getter
		private TextFilterField nameFilterField = new TextFilterField();
		
		@Getter
		private TextFilterField valueFilterField = new TextFilterField();
		
		@Override
		public boolean test(NameValue t) {
			return nameFilterField.getVergleich().getValue().getVergleich().test(t.getName(),nameFilterField.getText())
					&& valueFilterField.getVergleich().getValue().getVergleich().test(t.getValue(), valueFilterField.getText());
		}
		
		/**
		 * @param dataProvider
		 * since 12.03.2024
		 */
		public void setProvider(GridListDataView<NameValue> dataProvider) {
			nameFilterField.textField.clear();
			valueFilterField.textField.clear();

			getNameFilterField().getTextField().addValueChangeListener(e -> {
				dataProvider.setFilter(this);
			});
			getNameFilterField().getVergleich().addValueChangeListener(e -> {
				dataProvider.setFilter(this);
			});
			getValueFilterField().getTextField().addValueChangeListener(e -> {
				dataProvider.setFilter(this);
			});
			getValueFilterField().getVergleich().addValueChangeListener(e -> {
				dataProvider.setFilter(this);
			});
		}
	}
	
	private static void setFilterAndData(Grid<NameValue> grid, Collection<NameValue> items,	NameValueFilter filter) {
		GridListDataView<NameValue> dataProvider = grid.setItems(items);

		filter.setProvider(dataProvider);
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

		Application.beans.forEach((K, V) -> result.add(new NameValue(K, V)));

		return result;
	}

}
