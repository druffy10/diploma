package data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import page.MainPage;

import java.sql.*;

public class DBHelper {
    private static final QueryRunner runner = new QueryRunner();

    private DBHelper() {
    }

    private static Connection getPostgreSQLConn() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/app", "app", "pass");
    }

    @SneakyThrows
    public static MainPage.PurchaseId getLastPaymentIdForPostgre() {
        var idQuery = "SELECT id FROM payment_entity ORDER BY created DESC LIMIT 1";
        var conn = getPostgreSQLConn();
        var id = runner.query(conn, idQuery, new ScalarHandler<String>());
        return new MainPage.PurchaseId(id);
    }

    @SneakyThrows
    public static MainPage.PurchaseAmount getPaymentAmountForPostgre() {
        var amountQuery = "SELECT amount FROM payment_entity WHERE id = ?";
        var conn = getPostgreSQLConn();
        var lastPaymentId = getLastPaymentIdForPostgre();
        var amount = runner.query(conn, amountQuery, new ScalarHandler<>(), lastPaymentId.getId());
        return new MainPage.PurchaseAmount(amount.toString());
    }
}