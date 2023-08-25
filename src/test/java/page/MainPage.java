package page;

import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class MainPage {
    private final SelenideElement cardNumberField = $("[shouldHave(text('Номер'))]");
    private final SelenideElement monthField = $("[shouldHave(text('Месяц'))]");
    private final SelenideElement yearField = $("[shouldHave(text('Год'))]");
    private final SelenideElement cardOwnerField = $("[shouldHave(text('Владелец'))]");
    private final SelenideElement cvcField = $("[shouldHave(text('CVC'))]");
    private final SelenideElement button = $("[shouldHave(text('Продолжить'))]");
    private final SelenideElement error = $("[shouldHave(text('Ошибка'))]");
    private final SelenideElement success = $("[shouldHave(text('Успешно'))]");
    private static final String APPROVED_CARD = "4444 4444 4444 4441";
    private static final String DECLINED_CARD = "4444 4444 4444 4442";

    public void errorNotificationVisible() {
        error.shouldBe(visible);
    }

    public void successNotificationVisible() {
        success.shouldBe(visible);
    }

    public MainPage approvedCard (DataHelper.CardInfo cardInfo) {
        cardNumberField.setValue(APPROVED_CARD);
        monthField.setValue(cardInfo.getMonth());
        yearField.setValue(cardInfo.getYear());
        cardOwnerField.setValue(cardInfo.getCardOwner());
        cvcField.setValue(cardInfo.getCvc());
        button.click();
        return new MainPage();
    }

    public MainPage declinedCard (DataHelper.CardInfo cardInfo) {
        cardNumberField.setValue(DECLINED_CARD);
        monthField.setValue(cardInfo.getMonth());
        yearField.setValue(cardInfo.getYear());
        cardOwnerField.setValue(cardInfo.getCardOwner());
        cvcField.setValue(cardInfo.getCvc());
        button.click();
        return new MainPage();
    }
}
