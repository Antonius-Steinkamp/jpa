package de.anst.i18n;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.vaadin.flow.i18n.I18NProvider;

import de.anst.Utils;
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
	private final TranslationRepository translationRepository;

	public Translation18NProvider(final TranslationRepository translationRepository) {
		this.translationRepository = translationRepository;
		this.translationService = new Translation.Persister(translationRepository);
	}

	@Override
	public List<Locale> getProvidedLocales() {
		final List<Locale> result = Collections.unmodifiableList(Arrays.asList(Locale.GERMAN, Locale.ENGLISH, Locale.FRENCH, FINNISH));
		log.info("Locales: " + result);
		return result;
	}

	@Override
	public String getTranslation(String key, Locale locale, Object... params) {
//		String sql = "SELECT id from TRANSLATION WHERE " + Translation.Fields.original + " = '" + key + "' AND "+ Translation.Fields.locale + " = '" + locale.getLanguage() + "'";
//		Long id;
		Translation translation = null;
		List<Translation> trans = translationRepository.findByOriginalAndLocale(key, locale.getLanguage());
		if (Utils.hasValue(trans)) {
			translation  = trans.get(0);
		}

		if (translation != null) {
			final String result = MessageFormat.format(translation.getTranslated(), params);
			log.info("Translate '" + key + "/" + locale + "/" + " to '" + result + "'");
			// translation.updateRdate();
			translationService.update(translation);
			return result;
		} else {
			translation = new Translation();
			translation.setOriginal(key);
			translation.setLocale(locale.getLanguage());
			translation.setTranslated(untranslated(key));
			translationService.update(translation);
			final String result = MessageFormat.format(untranslated(key), params);
			log.info("Translate '" + key + "/" + locale + "/" + " to '" + result + "'");
			return result;
		}
	}
	
	private static String untranslated(String key) {
		return "<" + key + ">";
	}
}
