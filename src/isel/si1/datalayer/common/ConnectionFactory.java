package isel.si1.datalayer.common;

import isel.si1.console.Configuration;
import isel.si1.console.Configuration.DataBaseConfiguration;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class ConnectionFactory {

	public abstract Connection getConnection() throws ClassNotFoundException,
			SQLException;

	public String GetConnectionString() {

		DataBaseConfiguration database = Configuration.getInstance().Database;

		String url = "jdbc:sqlserver://" + database.Server;

		if (database.Port != null && database.Port.length() > 0) {
			url += ":" + database.Port + ";";
		} else {
			url += ";";
		}

		if (database.InstanceName != null && database.InstanceName.length() > 0) {
			url += "instanceName=" + database.InstanceName + ";";
		}

		if (database.Database != null && database.Database.length() > 0) {
			url += "databaseName=" + database.Database + ";";
		}
		if (database.IntegratedSecurity) {
			url += "integratedSecurity=true;";
		} else {
			url += "user=" + database.Username + ";password="
					+ database.Password + ";";
		}

		return url;
	}

}
