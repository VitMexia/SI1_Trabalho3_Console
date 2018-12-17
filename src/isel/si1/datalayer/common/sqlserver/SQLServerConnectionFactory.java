package isel.si1.datalayer.common.sqlserver;


import isel.si1.datalayer.common.ConnectionFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLServerConnectionFactory extends ConnectionFactory {

	private static SQLServerConnectionFactory connFactory=null;
	
	public static ConnectionFactory getInstance(){
		if( connFactory==null){
			connFactory = new SQLServerConnectionFactory();
		}
		return connFactory;
	}
	
	@Override
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		/* Private methods */

		Connection conn = null;

		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		//Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");

		// get a connection to DB
		conn = (Connection) DriverManager.getConnection(GetConnectionString());
		conn.setAutoCommit(false);
		
		return conn;
	}

}
