/**
 * ApplicationPropertiesAccess.java created 08.03.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 */
package de.anst.test.rest;

/**
 * ApplicationPropertiesAccess created 08.03.2024 by <a href="mailto:antonius.steinkamp@gmail.com">Antonius</a>
 *
 */
import java.io.IOException;
import java.io.InputStream;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import lombok.extern.java.Log;

@Log
public class ApplicationPropertiesAccess {

    public static void main(String[] args) {
        Properties properties = new Properties();

        try (InputStream input = ApplicationPropertiesAccess.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                log.info("Die Datei application.properties wurde nicht gefunden.");
                return;
            }

            properties.load(input);

            // Zugriff auf Werte
            String dbUrl = properties.getProperty("spring.datasource.url");
            String dbUsername = properties.getProperty("spring.datasource.username");
            
            try {
				Driver driver = DriverManager.getDriver(dbUrl);
				log.info("Version: " + driver.getMajorVersion() + "." + driver.getMinorVersion());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

            log.info("Database URL: " + dbUrl);
            log.info("Database Username: " + dbUsername);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

