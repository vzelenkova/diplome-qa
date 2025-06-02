package data;

import com.github.javafaker.Faker;

import java.util.Locale;

public class DataHelper {
    private static final Faker faker = new Faker(new Locale("en"));

    public static CardInfo getApprovedCard() {
        return new CardInfo("1111 2222 3333 4444", "12", "25", "IVAN PETROV", "123");
    }

    public static CardInfo getDeclinedCard() {
        return new CardInfo("5555 6666 7777 8888", "12", "25", "IVAN PETROV", "123");
    }

    public static CardInfo getInvalidCard() {
        return new CardInfo("0000 0000 0000 0000", "00", "00", "", "000");
    }
}
