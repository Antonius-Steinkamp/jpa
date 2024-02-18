/**
 * CrusPersonView.java created 07.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 */
package de.anst.i18n;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Collectors;

import org.vaadin.crudui.form.impl.field.provider.ComboBoxProvider;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.Result;
import com.vaadin.flow.data.binder.ValueContext;
import com.vaadin.flow.data.converter.Converter;
import com.vaadin.flow.data.renderer.LocalDateTimeRenderer;
import com.vaadin.flow.data.renderer.TextRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import de.anst.MainLayout;
import de.anst.ui.ALocalDateTimeRenderer;
import de.anst.ui.ExtGridCrud;
import lombok.extern.java.Log;

@PageTitle("Translation")
@Route(value = "stranslation", layout = MainLayout.class)
@Log
public class TranslationView extends VerticalLayout {

	private static final long serialVersionUID = TranslationView.class.hashCode();
	
	final ExtGridCrud<Translation> crud = new ExtGridCrud<Translation>(Translation.class);

	public TranslationView(Translation.Persister persister) {
		super();

		crud.setCrudListener(persister);
		crud.getGrid().getColumnByKey(Translation.Fields.rdate).setRenderer(new ALocalDateTimeRenderer<Translation>(Translation::getRdate));

		crud.getCrudFormFactory().setFieldProvider(Translation.Fields.locale, new LocaleStringProvider());

		addAndExpand(crud);
	}

	public static class LocaleStringProvider extends ComboBoxProvider<String> {
		/**
		 * the long serialVersionUID
		 * since 11.02.2024
		 */
		private static final long serialVersionUID = LocaleStringProvider.class.hashCode();

		public LocaleStringProvider() {
			super(null, Arrays.asList(Translation.Persister.knownLocales).stream().map(l -> l.getLanguage())
					.collect(Collectors.toList()), new TextRenderer<String>(), localeString -> {
						var loc = new Locale(localeString);
						return loc.toString();
					});

		}
	}
	
}
