package de.anst.about;

import java.io.File;
import java.net.URL;
import java.security.CodeSource;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootVersion;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.vaadin.crudui.crud.impl.GridCrud;

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
import de.anst.Application;
import de.anst.MainLayout;
import lombok.extern.java.Log;

@PageTitle("About")
@Route(value = "about", layout = MainLayout.class)
@RouteAlias(value = "/", layout = MainLayout.class)
@Log
public class AboutView extends VerticalLayout {

    /**
	 * the long serialVersionUID
	 * since 10.02.2024
	 */
	private static final long serialVersionUID = 1L;
	
	private long minLastModified = Long.MAX_VALUE;
	private long maxLastModified = Long.MIN_VALUE;

	public AboutView() {
        setSpacing(false);

        H2 header = new H2("Moin");
        header.addClassNames(Margin.Top.XLARGE, Margin.Bottom.MEDIUM);
        add(header);
        AUtils.getClassesInPackage("de.anst").forEach(clazz -> {
            CodeSource codeSource = clazz.getProtectionDomain().getCodeSource();
            if (codeSource != null) {
            	

            	URL location = codeSource.getLocation();
                File file = new File(location.getFile());
            	log.info(clazz.getName() + "(" + file.getAbsolutePath() + ")");
                long lastModified = file.lastModified();
                if (lastModified > maxLastModified) {
                	maxLastModified = lastModified;
                }
                if (lastModified < minLastModified) {
                	minLastModified = lastModified;
                }
            }
        });
        add( new Paragraph("Classes from " + AUtils.timeOfMillisString(maxLastModified)) );
        add( new Paragraph("Java Version " + System.getProperty("java.version") + " " + System.getProperty("java.vm.name") + " " + System.getProperty("java.vm.version") + " " + System.getProperty("java.vm.vendor")) );
        add( new Paragraph("Vaadin Version " + com.vaadin.flow.component.Component.class.getPackage().getImplementationVersion()));
        add( new Paragraph("Crud Version " + GridCrud.class.getPackage().getImplementationVersion()));
        add( new Paragraph("Spring Boot Version " + SpringBootVersion.getVersion()));
        add( new Paragraph("OS " + System.getProperty("os.name") + " " + System.getProperty("os.version") + " " + System.getProperty("os.arch") + " " + Runtime.getRuntime().maxMemory() / (1024 * 1024) + " MB Memory"));
        
        Image img = new Image("images/ok-check.png", "my logo");
        img.setWidth("200px");
        add(img);

        add(new Html("<div> Die Demo von  <a href=\"http://antonius.hopto.org\" target=\"_blank\">Antonius</a> </div>"));

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
    }

}
