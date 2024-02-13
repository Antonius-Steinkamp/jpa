/**
 * LocalDateTimeProvider.java created 11.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 */
package de.anst.ui;

import java.time.LocalDateTime;

import org.vaadin.crudui.form.FieldProvider;

import com.vaadin.flow.component.datetimepicker.DateTimePicker;

/**
 * LocalDateTimeProvider created 11.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 *
 */
public class LocalDateTimeProvider implements FieldProvider<DateTimePicker, LocalDateTime> {

	private static final long serialVersionUID = LocalDateTimeProvider.class.hashCode();

	@Override
	public DateTimePicker buildField(LocalDateTime t) {
		return new DateTimePicker(t);
	}

}
