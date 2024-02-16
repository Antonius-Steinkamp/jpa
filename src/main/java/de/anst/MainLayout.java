package de.anst;

import java.text.DecimalFormat;

import org.vaadin.crudui.layout.impl.VerticalCrudLayout;
import org.vaadin.lineawesome.LineAwesomeIcon;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.theme.lumo.LumoUtility;

import de.anst.about.AboutView;
import de.anst.i18n.TranslationView;
import de.anst.material.MaterialView;
import de.anst.material.einheit.EinheitView;
import de.anst.material.verpackung.VerpackungView;
import de.anst.parameter.ParameterView;
import de.anst.pearltype.PearlTypeView;
import de.anst.pearltype.stkelement.StkElementView;
import de.anst.person.PersonView;
import de.anst.segment.SegmentView;
import de.anst.testview.DetailsBasic;
import lombok.extern.java.Log;

/**
 * The main view is a top-level placeholder for other views.
 */
@Log
public class MainLayout extends AppLayout {

    /**
	 * the long serialVersionUID
	 * since 07.02.2024
	 */
	private static final long serialVersionUID = -1728498194537721292L;
	private H2 viewTitle;

    public MainLayout() {
        setPrimarySection(Section.DRAWER);
        addDrawerContent();
        addHeaderContent();
    }

    private void addHeaderContent() {
        DrawerToggle toggle = new DrawerToggle();
        toggle.setAriaLabel("Menu toggle");

        viewTitle = new H2();
        viewTitle.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);

        addToNavbar(true, toggle, viewTitle);
    }

    private void addDrawerContent() {
        H1 appName = new H1("JPA");
        appName.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);
        Header header = new Header(appName);

        Scroller scroller = new Scroller(createNavigation());

        addToDrawer(header, scroller, createFooter());
    }

    private static SideNav createNavigation() {
        SideNav nav = new SideNav();

        nav.addItem(new SideNavItem(nav.getTranslation("Personen"), PersonView.class, LineAwesomeIcon.PEOPLE_CARRY_SOLID.create()));
        
        SideNavItem messagesLink = new SideNavItem(nav.getTranslation("Material"), MaterialView.class, LineAwesomeIcon.BOOK_SOLID.create());
        messagesLink.addItem(new SideNavItem(nav.getTranslation("Einheit"), EinheitView.class, LineAwesomeIcon.BOOK_SOLID.create()));
        messagesLink.addItem(new SideNavItem(nav.getTranslation("Verpackung"), VerpackungView.class, LineAwesomeIcon.BOOK_SOLID.create()));
        messagesLink.setExpanded(false);
        nav.addItem(messagesLink);

        messagesLink = new SideNavItem(nav.getTranslation("PearlType"), PearlTypeView.class, LineAwesomeIcon.CAR_ALT_SOLID.create());
        messagesLink.addItem(new SideNavItem(nav.getTranslation("StkElement"), StkElementView.class, LineAwesomeIcon.BOOK_SOLID.create()));
        nav.addItem(messagesLink);

        nav.addItem(new SideNavItem(nav.getTranslation("i18n"), TranslationView.class, LineAwesomeIcon.COG_SOLID.create()));
        nav.addItem(new SideNavItem(nav.getTranslation("Parameter"), ParameterView.class, LineAwesomeIcon.COG_SOLID.create()));
        nav.addItem(new SideNavItem(nav.getTranslation("Test"), DetailsBasic.class, LineAwesomeIcon.COG_SOLID.create()));
        nav.addItem(new SideNavItem(nav.getTranslation("About"), AboutView.class, LineAwesomeIcon.FLUSHED.create()));

        nav.addItem(new SideNavItem(nav.getTranslation("Abschnitte"), SegmentView.class, LineAwesomeIcon.FLUSHED.create()));

        return nav;
    }

    private static Footer createFooter() {
        Footer layout = new Footer();

        layout.add(createMemoryButton());

        return layout;
    }


    private static Button createMemoryButton() {
    	return  new Button(getMemoryInfo(), e -> {
    		e.getSource().setText(getMemoryInfo());
    		UI.getCurrent().getPage().fetchCurrentURL(url -> log.info(url.toString()));
    	});
    }

    
    private static DecimalFormat format = new DecimalFormat("#,###");
    private static String getMemoryInfo( ) {
    	System.gc();
        long freeMemory = Runtime.getRuntime().freeMemory()/1024;
        long totalMemory = Runtime.getRuntime().totalMemory()/1024;

        var result =  format.format(freeMemory) + " / " + format.format(totalMemory);
        // log.info(result);
        
        return result;
    }
    
    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        viewTitle.setText(getCurrentPageTitle());
    }

    private String getCurrentPageTitle() {
        PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
        return title == null ? "" : title.value();
    }
}
