package data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CardInfo {
    private String cardNumber;
    private String month;
    private String year;
    private String holder;
    private String cvc;
}
