/**
 * GridWithFiltersView.java created 08.03.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 */
package de.anst.test.view;

/**
 * GridWithFiltersView created 08.03.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 *
 */
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;

@Route("grid-with-filters")
public class GridWithFiltersView extends VerticalLayout {

    public GridWithFiltersView() {
        // Erstellen Sie eine Liste von Dummy-Daten für das Grid
        List<Item> itemList = createDummyData();

        // Grid erstellen und Daten setzen
        Grid<Item> grid = new Grid<>(Item.class);
        grid.setItems(itemList);

        // Datenanbieter erstellen und dem Grid zuweisen
        ListDataProvider<Item> dataProvider = new ListDataProvider<>(itemList);
        grid.setDataProvider(dataProvider);

        // Textfilter-Layout erstellen
        HorizontalLayout filterLayout = new HorizontalLayout();

        // Textfilter für jeden Spaltenwert erstellen
        TextField nameFilter = new TextField("Name");
        nameFilter.addValueChangeListener(event -> dataProvider.addFilter(
                item -> item.getName().toLowerCase().contains(nameFilter.getValue().toLowerCase())));
        filterLayout.add(nameFilter);

        TextField ageFilter = new TextField("Age");
        ageFilter.addValueChangeListener(event -> dataProvider.addFilter(
                item -> String.valueOf(item.getAge()).contains(ageFilter.getValue())));
        filterLayout.add(ageFilter);

        // Hinzufügen des Grids und des Filterlayouts zur View
        add(filterLayout, grid);
    }

    // Methode zum Erstellen von Dummy-Daten
    private List<Item> createDummyData() {
        List<Item> itemList = new ArrayList<>();
        itemList.add(new Item("John Doe", 25));
        itemList.add(new Item("Jane Doe", 30));
        itemList.add(new Item("Bob Smith", 22));
        // Fügen Sie hier weitere Dummy-Daten hinzu, falls benötigt
        return itemList;
    }

    // POJO-Klasse für die Daten im Grid
    public static class Item {
        private String name;
        private int age;

        public Item(String name, int age) {
            this.name = name;
            this.age = age;
        }

        // Getter und Setter für die Attribute
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
