package es.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.ciudadescolar.instituto.Alumno;

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

    public List<Alumno> mostrarAlumnos() {
        List<Alumno> alumnos = null;

        Statement staAlumnos = null;
        ResultSet rstAlumnos = null;

        if (con != null) {
            try {
                staAlumnos = con.createStatement();
                rstAlumnos = staAlumnos.executeQuery("SELECT expediente, nombre, fecha_nac FROm alumnos");
                LOG.debug("Se ha ejecutado correctamente la sentencia SELECT");
            } catch (SQLException e) {
                LOG.error("Error al consultar alumnos [" + e.getMessage() + "]");
            } finally {
                try {
                    if (rstAlumnos != null)
                        rstAlumnos.close();
                    if (staAlumnos != null)
                        staAlumnos.close();
                } catch (SQLException e) {
                    LOG.error("Error durante el cierre de la conexion [" + e.getMessage() + "]");
                }
            }
        }

        return alumnos;
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
