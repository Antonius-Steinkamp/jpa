/**
 * DetailsBasic.java created 13.02.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 */
package de.anst.testview;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.ListItem;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.html.UnorderedList;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import de.anst.MainLayout;
import lombok.extern.java.Log;

@PageTitle("Test")
@Route(value = "details-basic", layout = MainLayout.class)

@Log
public class DetailsBasic extends VerticalLayout {

	private static final long serialVersionUID = DetailsBasic.class.hashCode();

	public DetailsBasic() {

		Span name = new Span("Sophia Williams");
		Span email = new Span("sophia.williams@company.com");
		Span phone = new Span("(501) 555-9128");

		VerticalLayout content = new VerticalLayout(name, email, phone);
		content.setSpacing(false);
		content.setPadding(false);

		Details details = new Details("Contact information", content);
		details.setOpened(false);

		add(details);

		ListItem[] items = { new ListItem("Blake Martin"), new ListItem("Caroline Clark"), new ListItem("Avery Torres"),
				new ListItem("Khloe Scott"), new ListItem("Camila Fisher"),
				new ListItem(new Html("<a href=\"https://en.wikipedia.org/wiki/Gavin_Lewis\">Gavin Lewis</a")),
				new ListItem("Isabella Powell"), new ListItem("Zoe Wilson") };

		UnorderedList ncontent = new UnorderedList(items);

		details = new Details("Members (" + items.length + ")", ncontent);
		details.setOpened(false);
		// details.addThemeVariants(DetailsVariant.FILLED);

		add(details);
		log.info(this.getClass().getSimpleName() + " created");

	}

	public static Component createDetails() {
		var layout = new VerticalLayout();
		layout.setSpacing(false);
		layout.setPadding(false);
		
		Span name = new Span("Sophia Williams");
		Span email = new Span("sophia.williams@company.com");
		Span phone = new Span("(501) 555-9128");

		VerticalLayout content = new VerticalLayout(name, email, phone);
		content.setSpacing(false);
		content.setPadding(false);

		Details details = new Details("Contact information", content);
		details.setOpened(false);

		layout.add(details);

		ListItem[] items = { new ListItem("Blake Martin"), new ListItem("Caroline Clark"), new ListItem("Avery Torres"),
				new ListItem("Khloe Scott"), new ListItem("Camila Fisher"),
				new ListItem(new Html("<a href=\"https://en.wikipedia.org/wiki/Gavin_Lewis\">Gavin Lewis</a")),
				new ListItem("Isabella Powell"), new ListItem("Zoe Wilson") };

		UnorderedList ncontent = new UnorderedList(items);

		details = new Details("Members (" + items.length + ")", ncontent);
		details.setOpened(false);
		// details.addThemeVariants(DetailsVariant.FILLED);

		layout.add(details);

		return layout;
	}
}
