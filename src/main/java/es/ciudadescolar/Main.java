package es.ciudadescolar;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.ciudadescolar.instituto.Alumno;
import es.util.DbManager;

public class Main {
        private static final Logger LOG = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {
        DbManager manager = new DbManager();

        List<Alumno> alumnos = manager.mostrarAlumnos();

        for (Alumno alumno : alumnos) {
            LOG.info(alumno.toString());
        }
        manager.cerrarBd();
    }
}