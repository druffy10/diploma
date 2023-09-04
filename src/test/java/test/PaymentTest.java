package test;

import data.DataHelper;
import data.DBHelper;
import org.junit.jupiter.api.Test;
import page.MainPage;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.assertEquals;


public class PaymentTest {
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
    void testGetPaymentAmountFromPostgre() {
        int expectedAmount = 45000; // Установите ожидаемую сумму здесь
        int amount = Integer.parseInt(DBHelper.getPaymentAmountForPostgre().getAmount());
        assertEquals(expectedAmount, amount);
    }

}
