package de.anst;

import org.vaadin.lineawesome.LineAwesomeIcon;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.orderedlayout.Scroller;
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
import de.anst.pearl.PearlTypeView;
import de.anst.person.PersonsCrudView;
import io.micrometer.observation.transport.Propagator.Getter;

/**
 * The main view is a top-level placeholder for other views.
 */
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

        nav.addItem(new SideNavItem(nav.getTranslation("Personen"), PersonsCrudView.class, LineAwesomeIcon.PEOPLE_CARRY_SOLID.create()));
        
        SideNavItem messagesLink = new SideNavItem(nav.getTranslation("Material"), MaterialView.class, LineAwesomeIcon.BOOK_SOLID.create());
        messagesLink.addItem(new SideNavItem(nav.getTranslation("Einheit"), EinheitView.class, LineAwesomeIcon.BOOK_SOLID.create()));
        messagesLink.addItem(new SideNavItem(nav.getTranslation("Verpackung"), VerpackungView.class, LineAwesomeIcon.BOOK_SOLID.create()));
        messagesLink.setExpanded(false);
        nav.addItem(messagesLink);
        nav.addItem(new SideNavItem(nav.getTranslation("PearlType"), PearlTypeView.class, LineAwesomeIcon.CAR_ALT_SOLID.create()));

        nav.addItem(new SideNavItem(nav.getTranslation("i18n"), TranslationView.class, LineAwesomeIcon.COG_SOLID.create()));
        nav.addItem(new SideNavItem(nav.getTranslation("Parameter"), ParameterView.class, LineAwesomeIcon.COG_SOLID.create()));
        nav.addItem(new SideNavItem(nav.getTranslation("About"), AboutView.class, LineAwesomeIcon.FLUSHED.create()));


        return nav;
    }

    private static Footer createFooter() {
        Footer layout = new Footer();

        return layout;
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
