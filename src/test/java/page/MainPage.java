package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MainPage {
    private final SelenideElement cardNumberField = $("input[placeholder='0000 0000 0000 0000']");
    private final SelenideElement monthField = $("input[placeholder='08']");
    private final SelenideElement yearField = $("input[placeholder='22']");
    private final SelenideElement cardOwnerField = $$(".input-group__input-case").findBy(Condition.text("Владелец")).$(".input__control");
    private final SelenideElement cvcField = $("input[placeholder='999']");
    private final SelenideElement button = $(byText("Продолжить"));
    private final SelenideElement error = $(byText("Ошибка"));
    private final SelenideElement success = $(byText("Успешно"));
    private static final String APPROVED_CARD = "4444 4444 4444 4441";
    private static final String DECLINED_CARD = "4444 4444 4444 4442";

    public void errorNotificationVisible() {
        error.shouldBe(visible);
    }

    public void successNotificationVisible() {
        success.shouldBe(visible);
    }

    public MainPage approvedCard (DataHelper.CardInfo cardInfo) {
        cardNumberField.click();
        cardNumberField.sendKeys(APPROVED_CARD);
        monthField.click();
        monthField.setValue(cardInfo.getMonth());
        yearField.click();
        yearField.setValue(cardInfo.getYear());
        cardOwnerField.click();
        cardOwnerField.setValue(cardInfo.getCardOwner());
        cvcField.click();
        cvcField.setValue(cardInfo.getCvc());
        button.click();
        return new MainPage();
    }

    public MainPage declinedCard (DataHelper.CardInfo cardInfo) {
        cardNumberField.click();
        cardNumberField.sendKeys(DECLINED_CARD);
        monthField.click();
        monthField.setValue(cardInfo.getMonth());
        yearField.click();
        yearField.setValue(cardInfo.getYear());
        cardOwnerField.click();
        cardOwnerField.setValue(cardInfo.getCardOwner());
        cvcField.click();
        cvcField.setValue(cardInfo.getCvc());
        button.click();
        return new MainPage();
    }
    public MainPage randomCard (DataHelper.CardInfo cardInfo) {
        cardNumberField.click();
        cardNumberField.sendKeys(cardInfo.getCardNumber());
        monthField.click();
        monthField.setValue(cardInfo.getMonth());
        yearField.click();
        yearField.setValue(cardInfo.getYear());
        cardOwnerField.click();
        cardOwnerField.setValue(cardInfo.getCardOwner());
        cvcField.click();
        cvcField.setValue(cardInfo.getCvc());
        button.click();
        return new MainPage();
    }
    public static class PurchaseAmount {
        private final String amount;

        public PurchaseAmount(String amount) {
            this.amount = amount;
        }

        public String getAmount() {
            return amount;
        }
    }

    public static class PurchaseId {
        private final String id;

        public PurchaseId(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }
        }
    }

