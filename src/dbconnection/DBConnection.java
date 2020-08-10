package dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public Connection createConnection() {

        String user = "root";
        String password = "";
        String url = "jdbc:mysql://localhost:3306/testejsp?serverTimezone=UTC";

        Connection connection = null;

        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
        } catch(Exception connectionError) {
            System.out.println("Falha na conexão com o banco: " + connectionError.getMessage());
        }

        return connection;
    }
}
