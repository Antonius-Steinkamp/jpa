/**
 * AntoniusLocalDateTimeRenderer.java created 11.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 */
package de.anst.ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import com.vaadin.flow.data.renderer.LocalDateTimeRenderer;
import com.vaadin.flow.function.SerializableSupplier;
import com.vaadin.flow.function.ValueProvider;

import de.anst.AUtils;

/**
 * AntoniusLocalDateTimeRenderer created 11.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 * Wird in Tabellen verwendet.
 */
public class ALocalDateTimeRenderer<T> extends LocalDateTimeRenderer<T> {
	
	private static final long serialVersionUID = ALocalDateTimeRenderer.class.hashCode();
	
	static DateTimeFormatter longTermFormatter = AUtils.DATETIME_FORMATTER;
	static DateTimeFormatter shortTermFormatter = AUtils.TIME_FORMATTER;
	static String nullRepresentation = "...";

	@Override
    protected String getFormattedValue(LocalDateTime dateTime) {
    	LocalDateTime yesterday = LocalDateTime.now().minusDays(1);
    	if ( dateTime ==  null ) {
    		return nullRepresentation;
    	}
    	if (dateTime.isBefore(yesterday) || dateTime.isAfter(LocalDateTime.now())) {
    		return longTermFormatter.format(dateTime);
    	} else {
    		return shortTermFormatter.format(dateTime); 
    	}
    }

	/**
	 * @param valueProvider
	 * since 11.02.2024
	 */
	public ALocalDateTimeRenderer(ValueProvider<T, LocalDateTime> valueProvider) {
		super(valueProvider);
	}
	

}
