package test;

import com.codeborne.selenide.logevents.SelenideLogger;
import data.DataHelper;
import data.DBHelper;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import page.MainPage;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.assertEquals;


public class PaymentTest {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }
    @Test
    void shouldApproveCard() throws InterruptedException {
        var mainPage = open("http://localhost:8080", MainPage.class);
        $(byText("Купить")).click();
        mainPage.approvedCard(DataHelper.getValidCardInfo());
        Thread.sleep(10000);
        mainPage.successNotificationVisible();
    }

    @Test
    void shouldDeclineCard() throws InterruptedException {
        var mainPage = open("http://localhost:8080", MainPage.class);
        $(byText("Купить")).click();
        mainPage.declinedCard(DataHelper.getInvalidCardInfo());
        Thread.sleep(5000);
        mainPage.errorNotificationVisible();
    }

    @Test
    void shouldDeclineRandomCard() throws InterruptedException {
        var mainPage = open("http://localhost:8080", MainPage.class);
        $(byText("Купить")).click();
        mainPage.randomCard(DataHelper.getRandomCardInfo());
        Thread.sleep(5000);
        mainPage.errorNotificationVisible();
    }

    @Test
    void shouldApproveCardToCredit() throws InterruptedException {
        var mainPage = open("http://localhost:8080", MainPage.class);
        $(byText("Купить в кредит")).click();
        mainPage.approvedCard(DataHelper.getValidCardInfo());
        Thread.sleep(5000);
        mainPage.successNotificationVisible();
    }

    @Test
    void shouldDeclineCardToCredit() throws InterruptedException {
        var mainPage = open("http://localhost:8080", MainPage.class);
        $(byText("Купить в кредит")).click();
        mainPage.declinedCard(DataHelper.getInvalidCardInfo());
        Thread.sleep(5000);
        mainPage.errorNotificationVisible();
    }

    @Test
    void shouldDeclineRandomCardToCredit() throws InterruptedException {
        var mainPage = open("http://localhost:8080", MainPage.class);
        $(byText("Купить в кредит")).click();
        mainPage.randomCard(DataHelper.getRandomCardInfo());
        Thread.sleep(5000);
        mainPage.errorNotificationVisible();
    }

    @Test
    void testGetPaymentAmountFromMySQL() {
        int expectedAmount = 45000;
        int amount = Integer.parseInt(DBHelper.getPaymentAmount().getAmount());
        assertEquals(expectedAmount, amount);
    }

    @Test
    void shouldMatchCardStatusApprove() throws InterruptedException {
        var mainPage = open("http://localhost:8080", MainPage.class);
        $(byText("Купить")).click();
        mainPage.approvedCard(DataHelper.getValidCardInfo());
        Thread.sleep(5000);
        String expectedStatus = "APPROVED";
        String status = DBHelper.getCardStat().getStatus();
        assertEquals(expectedStatus, status);
    }
    @Test
    void shouldMatchCardStatusDecline() throws InterruptedException{
        var mainPage = open("http://localhost:8080", MainPage.class);
        $(byText("Купить")).click();
        mainPage.declinedCard(DataHelper.getInvalidCardInfo());
        Thread.sleep(5000);
        String expectedStatus = "DECLINED";
        String status = DBHelper.getCardStat().getStatus();
        assertEquals(expectedStatus, status);
    }
    @Test
    void shouldMatchRandomCardStatusDecline() throws InterruptedException{
        var mainPage = open("http://localhost:8080", MainPage.class);
        $(byText("Купить")).click();
        mainPage.randomCard(DataHelper.getRandomCardInfo());
        Thread.sleep(5000);
        String expectedStatus = "DECLINED";
        String status = DBHelper.getCardStat().getStatus();
        assertEquals(expectedStatus, status);
    }
}
