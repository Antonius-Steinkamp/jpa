/**
 * ExtCrudFormFactory.java created 11.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 */
package de.anst.ui;

import java.time.LocalDateTime;

import org.vaadin.crudui.form.CrudFormConfiguration;
import org.vaadin.crudui.form.impl.form.factory.DefaultCrudFormFactory;

import com.vaadin.flow.component.HasValueAndElement;

import lombok.extern.java.Log;

/**
 * ExtCrudFormFactory created 11.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 *
 */
@Log
public class ExtCrudFormFactory<T> extends DefaultCrudFormFactory<T>{

	/**
	 * the long serialVersionUID
	 * since 12.02.2024
	 */
	private static final long serialVersionUID = ExtCrudFormFactory.class.hashCode();
	
	private LocalDateTimeProvider provider = new LocalDateTimeProvider();
	/**
	 * @param domainType
	 * since 11.02.2024
	 */
	public ExtCrudFormFactory(Class<T> domainType) {
		super(domainType);
		log.info("My new formfactory for class " + domainType.getName());
	}

	@Override
	protected HasValueAndElement<?,?> buildField(CrudFormConfiguration configuration, String property, Class<?> propertyType,T domainObject) throws InstantiationException, IllegalAccessException {
		HasValueAndElement<?,?> buildField = super.buildField(configuration, property, propertyType, domainObject);
		log.info("buildField property: " + property + " type: " + propertyType.getSimpleName() + " field: " + buildField);
		if (buildField ==  null && LocalDateTime.class.isAssignableFrom(propertyType)) {
			buildField = provider.buildField(LocalDateTime.now());
		}
		return buildField;
	}
}
