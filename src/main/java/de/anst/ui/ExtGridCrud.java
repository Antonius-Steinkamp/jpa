package de.anst.ui;

import org.vaadin.crudui.crud.impl.GridCrud;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid.MultiSortPriority;
import com.vaadin.flow.component.icon.VaadinIcon;

import de.anst.Utils;
import lombok.extern.java.Log;

@Log
public class ExtGridCrud<T> extends GridCrud<T>{

	/**
	 * the long serialVersionUID
	 * since 09.02.2024
	 */
	private static final long serialVersionUID = 37537473558735552L;

	public ExtGridCrud(Class<T> domainType) {
		super(domainType);
		
    	grid.setColumnReorderingAllowed(true);
    	String[] declaredPropertyNames = Utils.getDeclaredPropertyNames(domainType);
    	grid.setColumns(declaredPropertyNames);

    	grid.setMultiSort(true, MultiSortPriority.APPEND);
    	grid.getColumns().forEach(col -> {
    		col.setResizable(true);
    		col.setHeader(getTranslation(col.getHeaderText()));
    	});

        var crudFormFactory = getCrudFormFactory();
        crudFormFactory.setVisibleProperties(declaredPropertyNames);
        crudFormFactory.setUseBeanValidation(true);

    	var helpButton = new Button(VaadinIcon.QUESTION.create(), e -> log.info("Help will come"));
    	helpButton.getElement().setAttribute("title", "Help");
        getCrudLayout().addToolbarComponent(helpButton);


    	var filterButton = new Button(VaadinIcon.FILTER.create(), e -> log.info("Filter will come"));
    	filterButton.getElement().setAttribute("title", "Filter");
        getCrudLayout().addToolbarComponent(filterButton);



	}

}
