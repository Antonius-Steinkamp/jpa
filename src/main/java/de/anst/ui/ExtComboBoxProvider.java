/**
 * ExtComboBoxProvider.java created 09.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 */
package de.anst.ui;

import java.util.Collection;

import org.vaadin.crudui.form.impl.field.provider.ComboBoxProvider;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ItemLabelGenerator;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.TextRenderer;

/**
 * ExtComboBoxProvider created 09.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 *
 */
public class ExtComboBoxProvider<T> extends ComboBoxProvider<T> {

	/**
	 * the long serialVersionUID
	 * since 10.02.2024
	 */
	private static final long serialVersionUID = -3252238200827396118L;

	/**
	 * @param items
	 * since 09.02.2024
	 */
	public ExtComboBoxProvider(Collection<T> items) {
		super(items);
	}

	public ExtComboBoxProvider(Collection<T> items, ItemLabelGenerator<T> itemLabelGenerator) {
		super(null, items, new TextRenderer<T>(), itemLabelGenerator);
	}

	public ExtComboBoxProvider(String caption, Collection<T> items, ItemLabelGenerator<T> itemLabelGenerator) {
		super(caption, items, new TextRenderer<T>(), itemLabelGenerator);
	}

	/**
	 * @param caption
	 * @param items
	 * @param renderer
	 * @param itemLabelGenerator
	 * since 09.02.2024
	 */
	public ExtComboBoxProvider(String caption, Collection<T> items, ComponentRenderer<? extends Component, T> renderer,
			ItemLabelGenerator<T> itemLabelGenerator) {
		super(caption, items, renderer, itemLabelGenerator);
	}

	/**
	 * @param caption
	 * @param items
	 * since 09.02.2024
	 */
	public ExtComboBoxProvider(String caption, Collection<T> items) {
		super(caption, items);
	}

}
