package dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public Connection createConnection() {

        String user = "root";
        String password = "";
        String url = "jdbc:mysql//localhost/testejsp";

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch(Exception connectionError) {
            System.out.println("Falha na conexão com o banco: " + connectionError.getMessage());
        }

        return connection;
    }
}
