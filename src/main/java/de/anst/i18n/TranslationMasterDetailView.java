package de.anst.i18n;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.renderer.LocalDateTimeRenderer;
import com.vaadin.flow.internal.LocaleUtil;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;

import de.anst.MainLayout;

@PageTitle("Translation-Master-Detail")
@Route(value = "translation-master-detail/:translationID?/:action?(edit)", layout = MainLayout.class)
public class TranslationMasterDetailView extends Div implements BeforeEnterObserver {

	/**
	 * the long serialVersionUID since 08.02.2024
	 */
	private static final long serialVersionUID = 4316984827117716425L;

	private final String TRANSLATION_ID = "translationID";
	private final String TRANSLATION_EDIT_ROUTE_TEMPLATE = "translation-master-detail/%s/edit";

	private final Grid<Translation> grid = new Grid<>(Translation.class, false);

	private TextField original;
	private ComboBox<String> locale;
	private TextField translated;
	private DateTimePicker cdate;
	private DateTimePicker udate;
	private DateTimePicker rdate;

	private final Button cancel = new Button(getTranslation("Cancel"));
	private final Button save = new Button(getTranslation("Save"));
	private final Button insert = new Button(getTranslation("Insert"));
	private final Button delete = new Button(getTranslation("Delete"));

	private final BeanValidationBinder<Translation> binder;

	private Translation translation;

	private final Translation.Persister translationService;

	public TranslationMasterDetailView(TranslationRepository translationRepository) {
		this.translationService = new Translation.Persister(translationRepository);
		addClassNames("master-detail-view");

		// Create UI
		SplitLayout splitLayout = new SplitLayout();

		createGridLayout(splitLayout);
		createEditorLayout(splitLayout);

		add(splitLayout);

		final String DATE_TIME_FORMATTER = "HH:mm:ss dd.MM.yyyy";
		LocalDateTimeRenderer<Translation> cdateRenderer = new LocalDateTimeRenderer<>(Translation::getCdate,
				() -> DateTimeFormatter.ofPattern(DATE_TIME_FORMATTER));
		LocalDateTimeRenderer<Translation> udateRenderer = new LocalDateTimeRenderer<>(Translation::getUdate,
				() -> DateTimeFormatter.ofPattern(DATE_TIME_FORMATTER));
		LocalDateTimeRenderer<Translation> rdateRenderer = new LocalDateTimeRenderer<>(Translation::getRdate,
				() -> DateTimeFormatter.ofPattern(DATE_TIME_FORMATTER));

		// Configure Grid
		grid.addColumn(Translation.Fields.original).setAutoWidth(true);
		grid.addColumn(Translation.Fields.locale).setAutoWidth(true);
		grid.addColumn(Translation.Fields.translated).setAutoWidth(true);
		grid.addColumn(Translation.Fields.cdate).setRenderer(cdateRenderer).setAutoWidth(true);
		grid.addColumn(Translation.Fields.udate).setRenderer(udateRenderer).setAutoWidth(true);
		grid.addColumn(Translation.Fields.rdate).setRenderer(rdateRenderer).setAutoWidth(true);

		grid.setItems(query -> translationService.list(
				PageRequest.of(query.getPage(), query.getPageSize(), VaadinSpringDataHelpers.toSpringDataSort(query)))
				.stream());
		grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);

		// when a row is selected or deselected, populate form
		grid.asSingleSelect().addValueChangeListener(event -> {
			if (event.getValue() != null) {
				UI.getCurrent().navigate(String.format(TRANSLATION_EDIT_ROUTE_TEMPLATE, event.getValue().getId()));
			} else {
				clearForm();
				UI.getCurrent().navigate(TranslationMasterDetailView.class);
			}
		});

		// Configure Form
		binder = new BeanValidationBinder<>(Translation.class);

		// Bind fields. This is where you'd define e.g. validation rules

		binder.bindInstanceFields(this);

		cancel.addClickListener(e -> {
			clearForm();
			refreshGrid();
		});

		insert.addClickListener(e -> {
			try {
				this.translation = new Translation();
				binder.writeBean(this.translation);
				translationService.update(this.translation);
				clearForm();
				refreshGrid();
				Notification.show("Data inserted");
				UI.getCurrent().navigate(TranslationMasterDetailView.class);
			} catch (ObjectOptimisticLockingFailureException exception) {
				Notification n = Notification.show(
						"Error updating the data. Somebody else has updated the record while you were making changes.");
				n.setPosition(Position.MIDDLE);
				n.addThemeVariants(NotificationVariant.LUMO_ERROR);
			} catch (ValidationException validationException) {
				Notification.show("Failed to update the data. Check again that all values are valid");
			}
		});

		save.addClickListener(e -> {
			try {
				if (this.translation == null) {
					this.translation = new Translation();
				}
				binder.writeBean(this.translation);
				translationService.update(this.translation);
				clearForm();
				refreshGrid();
				Notification.show("Data updated");
				UI.getCurrent().navigate(TranslationMasterDetailView.class);
			} catch (ObjectOptimisticLockingFailureException exception) {
				Notification n = Notification.show(
						"Error updating the data. Somebody else has updated the record while you were making changes.");
				n.setPosition(Position.MIDDLE);
				n.addThemeVariants(NotificationVariant.LUMO_ERROR);
			} catch (ValidationException validationException) {
				Notification.show("Failed to update the data. Check again that all values are valid");
			}
		});
		save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

		delete.addClickListener(e -> {
			try {
				if (this.translation != null) {
					binder.writeBean(this.translation);
					translationRepository.deleteById(this.translation.getId());
					clearForm();
					refreshGrid();
					Notification.show("Data deleted");
				}
				UI.getCurrent().navigate(TranslationMasterDetailView.class);
			} catch (ObjectOptimisticLockingFailureException exception) {
				Notification n = Notification.show(
						"Error updating the data. Somebody else has updated the record while you were making changes.");
				n.setPosition(Position.MIDDLE);
				n.addThemeVariants(NotificationVariant.LUMO_ERROR);
			} catch (ValidationException validationException) {
				Notification.show("Failed to update the data. Check again that all values are valid");
			}
		});
	}

	@Override
	public void beforeEnter(BeforeEnterEvent event) {
		Optional<Long> translationId = event.getRouteParameters().get(TRANSLATION_ID).map(Long::parseLong);
		if (translationId.isPresent()) {
			Optional<Translation> translationFromBackend = translationService.get(translationId.get());
			if (translationFromBackend.isPresent()) {
				populateForm(translationFromBackend.get());
			} else {
				Notification.show(
						String.format("The requested translation was not found, ID = %s", translationId.get()), 3000,
						Notification.Position.BOTTOM_START);
				// when a row is selected but the data is no longer available,
				// refresh grid
				refreshGrid();
				event.forwardTo(TranslationMasterDetailView.class);
			}
		}
	}

	private static List<String> getLocalesAsStrings() {
		List<String> result = new ArrayList<>();
		for (Locale locale : LocaleUtil.getI18NProvider().get().getProvidedLocales()) {
			String localeName = locale.getLanguage();
			if (hasValue(localeName)) {
				result.add(localeName);
			}
		}

		return result;
	}

	private static boolean hasValue(String string) {
		return string != null && string.length() > 0;
	}

	private void createEditorLayout(SplitLayout splitLayout) {
		Div editorLayoutDiv = new Div();
		editorLayoutDiv.setClassName("editor-layout");

		Div editorDiv = new Div();
		editorDiv.setClassName("editor");
		editorLayoutDiv.add(editorDiv);

		FormLayout formLayout = new FormLayout();
		original = new TextField(Translation.Fields.original);
		locale = new ComboBox<String>(Translation.Fields.locale);
		locale.setItems(getLocalesAsStrings());

		translated = new TextField(Translation.Fields.translated);

		cdate = new DateTimePicker(Translation.Fields.cdate);
		udate = new DateTimePicker(Translation.Fields.udate);
		rdate = new DateTimePicker(Translation.Fields.rdate);

		formLayout.add(original, locale, translated, cdate, udate, rdate);

		editorDiv.add(formLayout);
		createButtonLayout(editorLayoutDiv);

		splitLayout.addToSecondary(editorLayoutDiv);
	}

	private void createButtonLayout(Div editorLayoutDiv) {
		HorizontalLayout buttonLayout = new HorizontalLayout();
		buttonLayout.setClassName("button-layout");
		cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
		insert.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
		delete.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
		buttonLayout.add(insert, save, delete, cancel);
		editorLayoutDiv.add(buttonLayout);
	}

	private void createGridLayout(SplitLayout splitLayout) {
		Div wrapper = new Div();
		wrapper.setClassName("grid-wrapper");
		splitLayout.addToPrimary(wrapper);
		wrapper.add(grid);
	}

	private void refreshGrid() {
		grid.select(null);
		grid.getDataProvider().refreshAll();
	}

	private void clearForm() {
		populateForm(null);
	}

	private void populateForm(Translation value) {
		this.translation = value;
		binder.readBean(this.translation);

	}
}
