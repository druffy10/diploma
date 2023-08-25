package data;

import org.apache.commons.dbutils.QueryRunner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBHelper {
    private static final QueryRunner runner = new QueryRunner();

    private DBHelper() {
    }

    private static Connection getConn() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
    }
}