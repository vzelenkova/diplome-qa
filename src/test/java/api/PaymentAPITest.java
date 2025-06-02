java
package api;

import data.DataHelper;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PaymentAPITest {
    @Test
    void shouldApproveWithValidCard() {
        var card = DataHelper.getApprovedCard();
        given()
            .contentType(ContentType.JSON)
            .body(card)
        .when()
            .post("http://localhost:8080/api/v1/pay")
        .then()
            .statusCode(200)
            .body("status", equalTo("APPROVED"));
    }

    @Test
    void shouldDeclineWithInvalidCard() {
        var card = DataHelper.getDeclinedCard();
        given()
            .contentType(ContentType.JSON)
            .body(card)
        .when()
            .post("http://localhost:8080/api/v1/pay")
        .then()
            .statusCode(200)
            .body("status", equalTo("DECLINED"));
    }
}
