package de.anst.i18n;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import com.vaadin.flow.i18n.I18NProvider;

/**
 * Simple Implementation for {@link I18NProvider}.
 * 
 * @author Antonius
 *
 */
// @Component
public class ToUppercase18NProvider implements I18NProvider {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4726383865139769509L;
	/*
	 * Use no-country versions, so that e.g. both en_US and en_GB work.
	 */
	public static final java.util.Locale FINNISH = new Locale("fi");

	@Override
	public List<Locale> getProvidedLocales() {
		final List<Locale> result = Collections.unmodifiableList(Arrays.asList(Locale.ENGLISH, Locale.FRENCH, FINNISH));
		// log.info("Locales: " + result);
		return result;
	}

	@Override
	public String getTranslation(String original, Locale locale, Object... params) {
		final String result = MessageFormat.format(original, params).toUpperCase();
		// log.info("Translate '" + original + "/" + locale + "/" + " parms to '" + result + "'");
		return result;
	}
}
