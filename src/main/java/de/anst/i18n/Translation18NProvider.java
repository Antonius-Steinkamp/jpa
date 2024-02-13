package de.anst.i18n;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Component;

import com.vaadin.flow.i18n.I18NProvider;

import de.anst.AUtils;
import de.anst.i18n.Translation.Persister;
import de.anst.parameter.Parameter;
import lombok.extern.java.Log;

/**
 * Simple Implementation for {@link I18NProvider}.
 * 
 * @author Antonius
 *
 */
@Component
@Log
public class Translation18NProvider implements I18NProvider {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4726383865139769509L;
	/*
	 * Use no-country versions, so that e.g. both en_US and en_GB work.
	 */
	public static final java.util.Locale FINNISH = new Locale("fi");

	private final Translation.Persister translationService;
	
	private String untranslatedPattern = "%s";

	public Translation18NProvider(final Translation.Persister translationService, final Parameter.Persister persister) {
		this.translationService = translationService;

		log.info(persister.count() + " Values in Parameters");
		Parameter findByName = persister.findByName(Parameter.Persister.UNTRANSLATED_PATTERN);
		if (findByName != null) {
			untranslatedPattern = findByName.getTextValue();
		}
	}

	@Override
	public List<Locale> getProvidedLocales() {
		final List<Locale> result = Collections.unmodifiableList(Arrays.asList(Persister.knownLocales));
		return result;
	}

	@Override
	public String getTranslation(String key, Locale locale, Object... params) {
		Translation translation = null;
		List<Translation> trans = translationService.findByOriginalAndLocale(key, locale.getLanguage());
		if (AUtils.hasValue(trans)) {
			translation  = trans.get(0);
		}

		if (translation != null) {
			final String result = MessageFormat.format(translation.getTranslated(), params);
			translation.setRdate(LocalDateTime.now());
			translationService.update(translation);
			return result;
		} else {
			translation = new Translation();
			translation.setOriginal(key);
			translation.setLocale(locale.getLanguage());
			translation.setTranslated(untranslated(key));
			translationService.update(translation);
			final String result = MessageFormat.format(untranslated(key), params);
			return result;
		}
	}
	
	private String untranslated(String key) {
		return String.format(untranslatedPattern, key);
	}
}
