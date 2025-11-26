package es.ciudadescolar;

import es.util.DbManager;

public class Main {
    public static void main(String[] args) {
        DbManager manager = new DbManager();
        manager.cerrarBd();
    }
}