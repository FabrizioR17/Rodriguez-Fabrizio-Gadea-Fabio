package com.backend.clinicaOdontologica.repository.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2Connection {
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:~/Rodriguez-Fabrizio-Gadea-Fabio","sa", "sa");
    }

    public static void ejecutarScriptInicial() {
        Connection connection = null;
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:~/Rodriguez-Fabrizio-Gadea-Fabio;INIT=RUNSCRIPT FROM 'src/main/create.sql'", "sa", "sa");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
