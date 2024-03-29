package data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.util.Locale;

public class DataHelper {
    private static final Faker faker = new Faker(new Locale("en"));

    private DataHelper() {

    }

    private static String generateCardNumber() {
        return faker.numerify("################");
    }

    private static String generateMonth() {
        return String.format("%02d", faker.random().nextInt(1, 12));
    }

    private static String generateYear() {
        return String.format("%02d", faker.random().nextInt(24, 28));
    }

    private static String generateCardOwner() {
        String firstName = faker.name().firstName().toUpperCase();
        String lastName = faker.name().lastName().toUpperCase();
        return firstName + " " + lastName;
    }

    private static String generateCvc() {
        return String.valueOf(faker.random().nextInt(100, 999));
    }

    @Value
    public static class CardInfo {
        String cardNumber;
        String month;
        String year;
        String cardOwner;
        String cvc;
    }

    public static CardInfo getValidCardInfo() {
        return new CardInfo("4444 4444 4444 4441", generateMonth(), generateYear(), generateCardOwner(), generateCvc());
    }

    public static CardInfo getInvalidCardInfo() {
        return new CardInfo("4444 4444 4444 4442", generateMonth(), generateYear(), generateCardOwner(), generateCvc());
    }

    public static CardInfo getRandomCardInfo() {
        return new CardInfo(generateCardNumber(), generateMonth(), generateYear(), generateCardOwner(), generateCvc());
    }
}

