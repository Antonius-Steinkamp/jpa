/**
 * NameValue.java created 16.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 */
package de.anst.about;

import lombok.Data;

/**
 * NameValue created 16.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 *
 */
@Data
public class NameValue {
	private String name;
	private String value;
	
	public NameValue(String name, String value) {
		this.name = name;
		this.value = value;
	}
}
