package de.anst.about;

import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;

import de.anst.MainLayout;

@PageTitle("About")
@Route(value = "about", layout = MainLayout.class)
@RouteAlias(value = "/", layout = MainLayout.class)
public class AboutView extends VerticalLayout {

    /**
	 * the long serialVersionUID
	 * since 10.02.2024
	 */
	private static final long serialVersionUID = 1L;

	public AboutView() {
        setSpacing(false);

        Image img = new Image("images/ok-check.png", "my logo");
        img.setWidth("200px");
        add(img);

        H2 header = new H2("Moin");
        header.addClassNames(Margin.Top.XLARGE, Margin.Bottom.MEDIUM);
        add(header);
        add(new Html("<div> Die Demo von  <a href=\"http://antonius.hopto.org\" target=\"_blank\">Antonius</a> </div>"));

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
    }

}
