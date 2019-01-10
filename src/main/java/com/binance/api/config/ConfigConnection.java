package com.binance.api.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigConnection {

	public Connection conectar() {
		final String connectionString = "jdbc:mysql://localhost/binance_teste?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		final String USERNAME = "root", PWD = "root";

		try {
			return DriverManager.getConnection(connectionString, USERNAME, PWD);
		} catch (SQLException e) {
			System.out.println("Erro ao se conectar: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
}
