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

    public static Connection getMySQLConn() throws SQLException {
        return DriverManager.getConnection(System.getProperty("dbUrl"), System.getProperty("dbUsername"), System.getProperty("dbPassword"));
    }

    @SneakyThrows
    public static MainPage.PurchaseId getLastPaymentId() {
        var idQuery = "SELECT id FROM payment_entity ORDER BY created DESC LIMIT 1";
        var conn = getMySQLConn();
        var id = runner.query(conn, idQuery, new ScalarHandler<String>());
        return new MainPage.PurchaseId(id);
    }

    @SneakyThrows
    public static MainPage.PurchaseAmount getPaymentAmount() {
        var amountQuery = "SELECT amount FROM payment_entity WHERE id = ?";
        var conn = getMySQLConn();
        var lastPaymentId = getLastPaymentId();
        var amount = runner.query(conn, amountQuery, new ScalarHandler<>(), lastPaymentId.getId());
        return new MainPage.PurchaseAmount(amount.toString());
    }
    @SneakyThrows
    public static MainPage.CardStatus getCardStat() {
        var amountQuery = "SELECT status FROM payment_entity WHERE id = ?";
        var conn = getMySQLConn();
        var lastPaymentId = getLastPaymentId();
        var status = runner.query(conn, amountQuery, new ScalarHandler<>(), lastPaymentId.getId());
        return new MainPage.CardStatus((String) status);
    }
}