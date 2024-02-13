package de.anst.data.db;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
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

			List<Row> rows = jdbc.query("select * from public.translation", new Row());
			rows.forEach(r -> r.getRow().forEach((k,v) -> log.info(k + " " + v)));
		}
	}

	static class Row implements RowMapper<Row>{
		@Getter @Setter
		private Map<String, Object> row = new HashMap<>();

		@Override
		public Row mapRow(ResultSet rs, int rowNum) throws SQLException {
			Row result = new Row();
			ResultSetMetaData metaData = rs.getMetaData();
			for (int i=0; i<metaData.getColumnCount(); i++) {
				result.getRow().put(metaData.getColumnName(i+1), rs.getObject(i+1));
			}
			return result;
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
