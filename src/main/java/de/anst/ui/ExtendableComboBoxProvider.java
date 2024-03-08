package de.anst.ui;

import java.util.Collection;

import org.vaadin.crudui.form.impl.field.provider.AbstractListingProvider;

import com.vaadin.flow.component.ItemLabelGenerator;
import com.vaadin.flow.component.combobox.ComboBox;

/**
 * @author Alejandro Duarte
 */
public class ExtendableComboBoxProvider extends AbstractListingProvider<ComboBox<String>, String> {
	private static final long serialVersionUID = ExtendableComboBoxProvider.class.hashCode();

    private ItemLabelGenerator<String> itemLabelGenerator;
    @SuppressWarnings("hiding")
	private Collection<String> items;

    public ExtendableComboBoxProvider(Collection<String> items) {
        super(items);
        this.items = items;
    }

    public ExtendableComboBoxProvider(String caption, Collection<String> items) {
        super(caption, items);
        this.items = items;
    }

    @Override
    protected ComboBox<String> buildAbstractListing() {
        ComboBox<String> field = new ComboBox<>();
        field.setAllowCustomValue(true);
        field.addCustomValueSetListener(e -> {
            String customValue = e.getDetail();
            items.add(customValue);
            field.setItems(items);
            field.setValue(customValue);
        });
        if(renderer != null) {
            field.setRenderer(renderer);
        }
        if (itemLabelGenerator != null) {
            field.setItemLabelGenerator(itemLabelGenerator);
        }
        field.setItems(items);
        return field;
    }

}
