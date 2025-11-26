package es.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Mario Garcia
 * @version 1.0.0
 */
public class DbManager {
    private static final Logger LOG = LoggerFactory.getLogger(DbManager.class);

    private Connection con = null;

    public DbManager() {
        try {
            // Registramos Driver ya que en las versiones antiguas es necesario
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://192.168.203.77:3306/dam2_2425", "dam2", "dam2");
            LOG.debug("Conexion establecida con exito");
        } catch (ClassNotFoundException e) {
            LOG.error("Error al registrar el Driver [" + e.getMessage() + "]");
        } catch (SQLException e) {
            LOG.error("Error al establecer la conexion con la Base de Datos [" + e.getMessage() + "]");
        }
    }

    public boolean cerrarBd() {
        if (con != null)
            try {
                con.close();
                LOG.debug("Conexion con la Base de Datos cerrada con exito");
                return true;
            } catch (SQLException e) {
                LOG.error("Error al cerrar la Conexion con la Base de Datos [" + e.getMessage() + "]");
            }

        return false;
    }
}
