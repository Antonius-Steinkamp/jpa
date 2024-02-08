package de.anst;

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

import de.anst.i18n.TranslationMasterDetailView;
import de.anst.person.PersonsCrudView;
import de.anst.person.PersonsVaadinView;
import de.anst.views.about.AboutView;

import org.vaadin.lineawesome.LineAwesomeIcon;

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

        nav.addItem(new SideNavItem("Persons (V)", PersonsVaadinView.class, LineAwesomeIcon.COLUMNS_SOLID.create()));
        nav.addItem(new SideNavItem("Persons (C)", PersonsCrudView.class, LineAwesomeIcon.PEOPLE_CARRY_SOLID.create()));
        nav.addItem(new SideNavItem("i18n", TranslationMasterDetailView.class, LineAwesomeIcon.LANGUAGE_SOLID.create()));
        nav.addItem(new SideNavItem("About", AboutView.class, LineAwesomeIcon.FILE.create()));

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
