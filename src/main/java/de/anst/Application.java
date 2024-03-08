package de.anst;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

import lombok.extern.java.Log;

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
@Log
public class Application implements AppShellConfigurator {

	/**
	 * the long serialVersionUID since 07.02.2024
	 */
	private static final long serialVersionUID = 2479169153706781509L;
	
	private static ApplicationContext applicationContext;
	
	public static Map<String,String> beans = new HashMap<>();

	public static void main(String[] args) {
		applicationContext = SpringApplication.run(Application.class, args);
		String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
		for (String beanName: beanDefinitionNames) {
			beans.put(beanName, applicationContext.getBean(beanName) + "");
		}
	}

}
