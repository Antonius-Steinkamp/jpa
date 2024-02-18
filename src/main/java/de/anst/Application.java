package de.anst;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

/**
 * The entry point of the Spring Boot application.
 *
 * Use the @PWA annotation make the application installable on phones, tablets
 * and some desktop browsers.
 *
 */
@SpringBootApplication
@Theme(value = "jpa", variant = Lumo.DARK)
@Push
public class Application implements AppShellConfigurator {

	/**
	 * the long serialVersionUID since 07.02.2024
	 */
	private static final long serialVersionUID = 2479169153706781509L;

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

	}

}
