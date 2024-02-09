/**
 * Utils.java created 09.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 */
package de.anst;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Utils created 09.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 *
 */
public class Utils {
	public static boolean hasValue(String s) {
		return s != null && s.length() > 0;
	}
	
	public static boolean hasValue(Collection<?> collection) {
		return collection != null && !collection.isEmpty();
	}
	
	/**
	 * @param clasz
	 * @return
	 * since 07.02.2024
	 */
	public static String[] getDeclaredPropertyNames(Class<?> clasz) {
		var fieldList = new ArrayList<>();
		for( Field field: clasz.getDeclaredFields()) {
			fieldList.add(field.getName());
		}
		return (String[]) fieldList.toArray(new String[fieldList.size()]);
	}
}
