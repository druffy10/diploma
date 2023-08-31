package test;

import data.DataHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.MainPage;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class PaymentTest {
    @BeforeEach
    public void openPage() {
        String url = System.getProperty("http://localhost:8080");
        open(url);
    }
    @Test
    void shouldApproveCard() throws InterruptedException {
        MainPage mainPage = new MainPage();
        $(byText("Купить")).click();
        mainPage.approvedCard(DataHelper.getValidCardInfo());
        Thread.sleep(5000);
        mainPage.successNotificationVisible();
    }

    @Test
    void shouldDeclineCard() throws InterruptedException {
        MainPage mainPage = new MainPage();
        $(byText("Купить")).click();
        mainPage.declinedCard(DataHelper.getInvalidCardInfo());
        Thread.sleep(5000);
        mainPage.errorNotificationVisible();
    }

}
