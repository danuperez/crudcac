package com.mycompany.crudcac.model;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

/**
 *
 * @author dani
 */
public class Conexion {

    private static final String URL_DB = "jdbc:mysql://root:root@localhost:3306/crudcac?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static Connection con;
    private static BasicDataSource dataSource;

    private Conexion() {
    }

    public static DataSource getDataSource() {
        if (dataSource == null) {
            try {
                dataSource = new BasicDataSource();
                dataSource.setUrl(URL_DB);
                dataSource.setInitialSize(50);
            } catch (Exception ex) {
                throw new RuntimeException("Error de E/S al leer config de BBDD", ex);
            }
        }
        return dataSource;
    }

    public static Connection getConnection() throws SQLException {
        return getDataSource().getConnection();
    }
}
