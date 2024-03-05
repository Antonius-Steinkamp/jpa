package de.anst.ui;

import org.vaadin.crudui.crud.impl.GridCrud;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid.MultiSortPriority;
import com.vaadin.flow.component.icon.VaadinIcon;

import de.anst.AUtils;
import lombok.extern.java.Log;

@Log
public class ExtGridCrud<T> extends GridCrud<T>{

	private static final long serialVersionUID = ExtGridCrud.class.hashCode();
	
	private boolean seeAllProperties = true;
	
	private String[] declaredPropertyNames = AUtils.getDeclaredPropertyNamesArray(domainType);
	private String[] allPropertyNames = AUtils.getAllPropertyNamesArray(domainType);

	private void toggleColumns() {
		if (seeAllProperties) {
	    	grid.setColumns(declaredPropertyNames);
		} else {
	    	grid.setColumns(allPropertyNames);
		}
		
		seeAllProperties = !seeAllProperties;
	}
	
	public ExtGridCrud(Class<T> domainType) {
		super(domainType, new ExtCrudFormFactory<>(domainType));
		
    	grid.setColumnReorderingAllowed(true);
    	String[] declaredPropertyNames = AUtils.getDeclaredPropertyNamesArray(domainType);
//    	log.info("Declared Columns = " + Arrays.asList(declaredPropertyNames));
//    	log.info("All Columns = " + Arrays.asList(allPropertyNames));
    
    	toggleColumns();
    	
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

    	var keyButton = new Button(VaadinIcon.KEY.create(), e ->  toggleColumns());
    	keyButton.getElement().setAttribute("title", "All columns");
        getCrudLayout().addToolbarComponent(keyButton);



	}

}
