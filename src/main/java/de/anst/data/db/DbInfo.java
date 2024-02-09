package de.anst.data.db;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

// @Component
@RequiredArgsConstructor
@Log
public class DbInfo implements CommandLineRunner {

	private final JdbcTemplate jdbc;

	@Override
	public void run(String... args) throws Exception {
		try (Connection connection = jdbc.getDataSource().getConnection()) {
			DatabaseMetaData metaData = connection.getMetaData();
			int databaseMajorVersion = metaData.getDatabaseMajorVersion();
			log.info("Database Major Version is " + databaseMajorVersion);
	
			String catalogSeparator = metaData.getCatalogSeparator();
			log.info("Catalog separator is '" + catalogSeparator + "'");
			try (ResultSet catalogs = metaData.getCatalogs()) {
				printResultSet(catalogs);
			}
			
			try (ResultSet schemas = metaData.getSchemas()) {
				printResultSet(schemas);
			}

			try (ResultSet schemas = metaData.getTableTypes()) {
				printResultSet(schemas);
			}

			try (ResultSet schemas = metaData.getTables(null, null, "%", null)) {
				printResultSet(schemas);
			}

			//metaData.getTables(catalogSeparator, catalogSeparator, catalogSeparator, args)
			// metaData.getTableTypes()
		}
	}

	private static void printResultSet(ResultSet resultSet) throws SQLException {
		ResultSetMetaData rsmd = resultSet.getMetaData();
		int columnsNumber = rsmd.getColumnCount();
		while (resultSet.next()) {
			StringBuffer row = new StringBuffer("");
			for (int i = 1; i <= columnsNumber; i++) {
				if (i > 1) {
					row.append(",  ");
				}
				String columnValue = resultSet.getString(i);
				row.append(rsmd.getColumnName(i) + " is '" + columnValue + "'");
			}
			log.info(row.toString());
		}
	}
}
