package de.anst.about;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.security.CodeSource;

import org.springframework.boot.SpringBootVersion;
import org.vaadin.crudui.crud.impl.GridCrud;

import com.jayway.jsonpath.InvalidPathException;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;

import de.anst.AUtils;
import de.anst.MainLayout;
import lombok.extern.java.Log;

@PageTitle("About")
@Route(value = "about", layout = MainLayout.class)
@RouteAlias(value = "/", layout = MainLayout.class)
@Log
public class AboutView extends VerticalLayout {

	/**
	 * the long serialVersionUID since 10.02.2024
	 */
	private static final long serialVersionUID = 1L;

	public AboutView() {
		setSpacing(false);
		setPadding(false);

		H2 header = new H2("Moin");
		header.addClassNames(Margin.Top.XLARGE, Margin.Bottom.MEDIUM);
		add(header);
		/*
		long maxLastModified = Long.MIN_VALUE;
		for (var clazz : AUtils.getClassesInPackage("de.anst")) {
			 CodeSource codeSource = clazz.getProtectionDomain().getCodeSource(); if
			 (codeSource != null) { URL location = codeSource.getLocation(); File file =
			 new File(location.getFile() + clazz.getName().replace('.', '/') + ".class");
			 long lastModified = file.lastModified(); log.info(clazz.getName() + "(" +
			 file.getAbsolutePath() + ") " + AUtils.timeOfMillisString(maxLastModified) +
			 " (" + lastModified + ")"); if (lastModified > maxLastModified) {
			 maxLastModified = lastModified; } }
			 
		}
	*/

		// add(DetailsBasic.createDetails());

		Image img = new Image("images/ok-check.png", "my logo");
		img.setWidth("200px");
		add(img);

		add(createHtml("Demo von <a href=\"http://antonius.hopto.org\" target=\"_blank\">Antonius</a>"));
//		add(createHtml("Demo vom " + AUtils.timeOfMillisString(maxLastModified)	+ " von <a href=\"http://antonius.hopto.org\" target=\"_blank\">Antonius</a>"));
		add(new Paragraph("mit"));
		add(createHtml("Java Version " + System.getProperty("java.version") + " " + System.getProperty("java.vm.name")
				+ " " + System.getProperty("java.vm.version") + " " + System.getProperty("java.vm.vendor")));
		add(createHtml("Vaadin " + com.vaadin.flow.component.Component.class.getPackage().getImplementationVersion()));
		add(createHtml("Crud Version " + GridCrud.class.getPackage().getImplementationVersion()));
		add(createHtml("Spring Boot Version " + SpringBootVersion.getVersion()));
		add(createHtml("OS " + System.getProperty("os.name") + " " + System.getProperty("os.version") + " "
				+ System.getProperty("os.arch") + " " + Runtime.getRuntime().maxMemory() / (1024 * 1024)
				+ " MB Memory"));

		setSizeFull();
		setJustifyContentMode(JustifyContentMode.CENTER);
		setDefaultHorizontalComponentAlignment(Alignment.CENTER);
		getStyle().set("text-align", "center");
	}

	/**
	 * Dekorieren mit einem {@code<div>}
	 * 
	 * @param String html
	 * 
	 * @return String der Übnergabestring mit einem {@code<div>} drumrum. since
	 *         13.02.2024
	 */
	private static Html createHtml(String html) {
		return new Html("<div>" + html + "</div>");
	}
}
