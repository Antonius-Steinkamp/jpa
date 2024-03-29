package de.anst;

import org.vaadin.lineawesome.LineAwesomeIcon;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.theme.lumo.Lumo;
import com.vaadin.flow.theme.lumo.LumoUtility;

import de.anst.about.AboutView;
import de.anst.about.EnvView;
import de.anst.i18n.TranslationView;
import de.anst.parameter.ParameterView;
import de.anst.person.PersonView;
import de.anst.test.view.DetailsBasic;
import de.anst.vpc.cc.ControlCycleView;
import de.anst.vpc.fis.FisMessageView;
import de.anst.vpc.material.MaterialView;
import de.anst.vpc.material.einheit.Einheit;
import de.anst.vpc.material.verpackung.VerpackungView;
import de.anst.vpc.pearl.PearlView;
import de.anst.vpc.pearl.stk.StkView;
import de.anst.vpc.pearltype.PearlTypeView;
import de.anst.vpc.pearltype.stkelement.StkElementView;
import de.anst.vpc.segment.SegmentView;
import de.anst.vpc.segment.bdo.VerbauortView;
import de.anst.vpc.segment.meldepunkt.MeldepunktView;

/**
 * The main view is a top-level placeholder for other views.
 */
// @Log
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
        
        nav.addItem(new SideNavItem(nav.getTranslation("i18n"), TranslationView.class, LineAwesomeIcon.COG_SOLID.create()));
        nav.addItem(new SideNavItem(nav.getTranslation("Parameter"), ParameterView.class, LineAwesomeIcon.COG_SOLID.create()));
        nav.addItem(new SideNavItem(nav.getTranslation("Umgebung"), EnvView.class, LineAwesomeIcon.FLUSHED.create()));
        nav.addItem(new SideNavItem(nav.getTranslation("Test"), DetailsBasic.class, LineAwesomeIcon.COG_SOLID.create()));
        nav.addItem(new SideNavItem(nav.getTranslation("About"), AboutView.class, LineAwesomeIcon.FLUSHED.create()));
        nav.addItem(new SideNavItem(nav.getTranslation("ControlCycle"), ControlCycleView.class, LineAwesomeIcon.FLUSHED.create()));
        nav.addItem(new SideNavItem(nav.getTranslation("Perlen"), PearlView.class, LineAwesomeIcon.FLUSHED.create()));
        nav.addItem(new SideNavItem(nav.getTranslation("Verbau"), StkView.class, LineAwesomeIcon.FLUSHED.create()));
        nav.addItem(new SideNavItem(nav.getTranslation("FisMeldungen"), FisMessageView.class, LineAwesomeIcon.FLUSHED.create()));

        SideNavItem messagesLink = new SideNavItem(nav.getTranslation("Material"), MaterialView.class, LineAwesomeIcon.BOOK_SOLID.create());
        messagesLink.addItem(new SideNavItem(nav.getTranslation("Einheit"), Einheit.EinheitView.class, LineAwesomeIcon.BOOK_SOLID.create()));
        messagesLink.addItem(new SideNavItem(nav.getTranslation("Verpackung"), VerpackungView.class, LineAwesomeIcon.BOOK_SOLID.create()));
        messagesLink.setExpanded(false);
        nav.addItem(messagesLink);

        messagesLink = new SideNavItem(nav.getTranslation("PearlType"), PearlTypeView.class, LineAwesomeIcon.CAR_ALT_SOLID.create());
        messagesLink.addItem(new SideNavItem(nav.getTranslation("StkElement"), StkElementView.class, LineAwesomeIcon.BOOK_SOLID.create()));
        nav.addItem(messagesLink);


        messagesLink = new SideNavItem(nav.getTranslation("Segmente"), SegmentView.class, LineAwesomeIcon.FLUSHED.create());
        messagesLink.addItem(new SideNavItem(nav.getTranslation("Meldepunkte"), MeldepunktView.class, LineAwesomeIcon.FLUSHED.create()));
        messagesLink.addItem(new SideNavItem(nav.getTranslation("Verbauorte"), VerbauortView.class, LineAwesomeIcon.FLUSHED.create()));
        nav.addItem(messagesLink);
        

        return nav;
    }

    private Footer createFooter() {
        return new Footer(createThemeToggle());
    }

    public Checkbox createThemeToggle() {
		var themeToggle = new Checkbox("Dark theme");
		themeToggle.addValueChangeListener(e -> {
			setTheme(e.getValue());
		});
		
		return themeToggle;

    }
    
    private void setTheme(boolean dark) {
		var js = "document.documentElement.setAttribute('theme', $0)";

		getElement().executeJs(js, dark ? Lumo.DARK : Lumo.LIGHT);
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
