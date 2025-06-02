package ui;

import com.codeborne.selenide.Configuration;
import data.DataHelper;
import data.SQLHelper;
import org.junit.jupiter.api.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class PaymentUITest {
    @BeforeAll
    static void setup() {
        Configuration.browser = "chrome";
        Configuration.headless = true;
    }

    @BeforeEach
    void openPage() {
        open("http://localhost:8080");
        $$("button").findBy(text("Купить")).click();
        SQLHelper.cleanDatabase();
    }

    @Test
    void shouldApproveWithValidCard() {
        var card = DataHelper.getApprovedCard();
        fillForm(card);
        $(".notification__content").shouldHave(text("Операция одобрена Банком."));
        Assertions.assertEquals("APPROVED", SQLHelper.getLatestStatus());
    }

    @Test
    void shouldDeclineWithDeclinedCard() {
        var card = DataHelper.getDeclinedCard();
        fillForm(card);
        $(".notification__content").shouldHave(text("Ошибка! Банк отказал в проведении операции."));
        Assertions.assertEquals("DECLINED", SQLHelper.getLatestStatus());
    }

    void fillForm(data.CardInfo card) {
        $$(".input__control").get(0).setValue(card.getCardNumber());
        $$(".input__control").get(1).setValue(card.getMonth());
        $$(".input__control").get(2).setValue(card.getYear());
        $$(".input__control").get(3).setValue(card.getHolder());
        $$(".input__control").get(4).setValue(card.getCvc());
        $$("button").findBy(text("Продолжить")).click();
    }
}
