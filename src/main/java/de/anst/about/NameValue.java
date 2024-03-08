/**
 * NameValue.java created 16.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 */
package de.anst.about;

import lombok.Data;
import lombok.experimental.FieldNameConstants;

/**
 * NameValue created 16.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 *
 */
@Data
@FieldNameConstants
public class NameValue {
	private String name;
	private String value;
	
	public NameValue(String name, String value) {
		this.name = name;
		this.value = value;
	}
}
