import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {
    private Statement statement;
    private Connection connection;

    public DB() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String dbURL = "jdbc:mysql://localhost:3306/supermarkt";
        connection = DriverManager.getConnection(dbURL, "root", "Mai-91");
        statement = connection.createStatement();
    }

    public Statement getStatement() {
        return statement;
    }

    public void close() throws Exception {
        this.statement.close();
        this.connection.close();
    }

    public Connection getConnection() {
        return connection;
    }
}
