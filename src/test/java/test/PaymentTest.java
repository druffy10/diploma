package test;

import com.codeborne.selenide.logevents.SelenideLogger;
import data.DataHelper;
import data.DBHelper;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import page.MainPage;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PaymentTest extends MethodOrderer.OrderAnnotation {
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }
    @Order(1)
    @Test
    void shouldApproveCard() throws InterruptedException {
        var mainPage = open("http://localhost:8080", MainPage.class);
        $(byText("Купить")).click();
        mainPage.approvedCard(DataHelper.getValidCardInfo());
        Thread.sleep(12000);
        mainPage.successNotificationVisible();
    }
    @Order(2)
    @Test
    void shouldDeclineCard() throws InterruptedException {
        var mainPage = open("http://localhost:8080", MainPage.class);
        $(byText("Купить")).click();
        mainPage.declinedCard(DataHelper.getInvalidCardInfo());
        Thread.sleep(12000);
        mainPage.errorNotificationVisible();
    }
    @Order(3)
    @Test
    void shouldDeclineRandomCard() throws InterruptedException {
        var mainPage = open("http://localhost:8080", MainPage.class);
        $(byText("Купить")).click();
        mainPage.randomCard(DataHelper.getRandomCardInfo());
        Thread.sleep(12000);
        mainPage.errorNotificationVisible();
    }
    @Order(3)
    @Test
    void shouldApproveCardToCredit() throws InterruptedException {
        var mainPage = open("http://localhost:8080", MainPage.class);
        $(byText("Купить в кредит")).click();
        mainPage.approvedCard(DataHelper.getValidCardInfo());
        Thread.sleep(20000);
        mainPage.successNotificationVisible();
    }
    @Order(4)
    @Test
    void shouldDeclineCardToCredit() throws InterruptedException {
        var mainPage = open("http://localhost:8080", MainPage.class);
        $(byText("Купить в кредит")).click();
        mainPage.declinedCard(DataHelper.getInvalidCardInfo());
        Thread.sleep(12000);
        mainPage.errorNotificationVisible();
    }
    @Order(5)
    @Test
    void shouldDeclineRandomCardToCredit() throws InterruptedException {
        var mainPage = open("http://localhost:8080", MainPage.class);
        $(byText("Купить в кредит")).click();
        mainPage.randomCard(DataHelper.getRandomCardInfo());
        Thread.sleep(12000);
        mainPage.errorNotificationVisible();
    }
    @Order(6)
    @Test
    void testGetPaymentAmountFromMySQL() {
        int expectedAmount = 45000;
        int amount = Integer.parseInt(DBHelper.getPaymentAmount().getAmount());
        assertEquals(expectedAmount, amount);
    }
    @Order(7)
    @Test
    void shouldMatchCardStatusApprove() throws InterruptedException {
        var mainPage = open("http://localhost:8080", MainPage.class);
        $(byText("Купить")).click();
        mainPage.approvedCard(DataHelper.getValidCardInfo());
        Thread.sleep(12000);
        String expectedStatus = "APPROVED";
        String status = DBHelper.getCardStat().getStatus();
        assertEquals(expectedStatus, status);
    }
    @Order(8)
    @Test
    void shouldMatchCardStatusDecline() throws InterruptedException {
        var mainPage = open("http://localhost:8080", MainPage.class);
        $(byText("Купить")).click();
        mainPage.declinedCard(DataHelper.getInvalidCardInfo());
        Thread.sleep(12000);
        String expectedStatus = "DECLINED";
        String status = DBHelper.getCardStat().getStatus();
        assertEquals(expectedStatus, status);
    }
    @Order(9)
    @Test
    void shouldMatchRandomCardStatusDecline() throws InterruptedException {
        var mainPage = open("http://localhost:8080", MainPage.class);
        $(byText("Купить")).click();
        mainPage.randomCard(DataHelper.getRandomCardInfo());
        Thread.sleep(12000);
        String expectedStatus = "DECLINED";
        String status = DBHelper.getCardStat().getStatus();
        assertEquals(expectedStatus, status);
    }
}
