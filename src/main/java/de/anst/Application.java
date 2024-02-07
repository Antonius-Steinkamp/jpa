package de.anst;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

import de.anst.person.PersonRepository;
import lombok.extern.java.Log;

import javax.sql.DataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.sql.init.SqlDataSourceScriptDatabaseInitializer;
import org.springframework.boot.autoconfigure.sql.init.SqlInitializationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * The entry point of the Spring Boot application.
 *
 * Use the @PWA annotation make the application installable on phones, tablets
 * and some desktop browsers.
 *
 */
@SpringBootApplication
@Theme(value = "jpa", variant = Lumo.DARK)
@Log
public class Application implements AppShellConfigurator {

	/**
	 * the long serialVersionUID since 07.02.2024
	 */
	private static final long serialVersionUID = 2479169153706781509L;

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
		for (String ctx : context.getBeanDefinitionNames()) {
			// log.info(ctx);
		}

	}

	@Bean
	SqlDataSourceScriptDatabaseInitializer dataSourceScriptDatabaseInitializer(DataSource dataSource,
			SqlInitializationProperties properties, PersonRepository repository) {
		// This bean ensures the database is only initialized when empty
		return new SqlDataSourceScriptDatabaseInitializer(dataSource, properties) {
			@Override
			public boolean initializeDatabase() {
				if (repository.count() == 0L) {
					return super.initializeDatabase();
				}
				return false;
			}
		};
	}
}
