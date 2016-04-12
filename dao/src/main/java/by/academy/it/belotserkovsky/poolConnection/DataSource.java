package by.academy.it.belotserkovsky.poolConnection;

import by.academy.it.belotserkovsky.managers.DataBaseManager;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Description Pool Connection based c3po
 * Created by Kostya on 03.04.2016.
 */
public class DataSource {

    private static DataSource datasource;
    private ComboPooledDataSource cpds;


    private DataSource() throws IOException, SQLException, PropertyVetoException {
        cpds = new ComboPooledDataSource();
        cpds.setDriverClass(DataBaseManager.getProperty("jdbc.param.driver")); //loads the jdbc driver
        cpds.setJdbcUrl(DataBaseManager.getProperty("jdbc.param.url"));
        cpds.setUser(DataBaseManager.getProperty("user.param.login"));
        cpds.setPassword(DataBaseManager.getProperty("user.param.password"));

        // the settings below are optional -- c3p0 can work with defaults
        cpds.setMinPoolSize(5);
        cpds.setAcquireIncrement(5);
        cpds.setMaxPoolSize(20);
        cpds.setMaxStatements(180);

    }

    public static DataSource getInstance() throws IOException, SQLException, PropertyVetoException {
        if (datasource == null) {
            datasource = new DataSource();
            return datasource;
        } else {
            return datasource;
        }
    }

    public Connection getConnection() throws SQLException {
        return this.cpds.getConnection();
    }

}
