/**
Hive JDBC Driver is available here - https://www.cloudera.com/downloads/connectors/hive/jdbc/
Compile -- javac -cp .:./lib/HiveJDBC42.jar HiveJDBC.java
Execute -- java -cp .:./lib/HiveJDBC42.jar HiveJDBC
*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class HiveJDBC {

	// Parameters
	static String CONNECTION_URL = "<HIVE_JDBC_URL>"; // ex: jdbc:hive2://<host>:443/default;transportMode=http;httpPath=cliservice;UID=<your_username>;PWD=<your_password>;
	static String QUERY = "<HIVE_QUERY>";

	/**
	 * Main method
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			// establish connection
			Connection connection = null;
			connection = DriverManager.getConnection(CONNECTION_URL);

			// execute query
			execQry(connection);

			// close connection
			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Execute Query
	 *
	 * @param connection
	 */
	public static void execQry(Connection connection) {
		try {
			// execute query
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(QUERY);

			// print output
			System.out.println("Hive test ------- ");
			while (rs.next()) {
				// print 1st column values
				System.out.println(rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Wait method to test queries after intervals for troubleshooting
	 *
	 * @param ms
	 */
	public static void wait(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			System.err.format("IOException: %s%n", e);
		}
	}

}
