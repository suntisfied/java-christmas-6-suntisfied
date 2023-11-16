package christmas.promotion.datepromotion;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.promotion.Discount;
import christmas.view.input.Date;
import christmas.view.input.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class SpecialTest {
    @ParameterizedTest
    @CsvSource({
            "'티본스테이크-1,레드와인-1', 3, 1000",
            "'티본스테이크-1,레드와인-1', 4, 0",
            "'타파스-1,제로콜라-1', 4, 0",
    })
    public void calculateSpecialDiscount(String orderList, int date, int discount) {
        Order order = new Order(orderList);
        DateBenefit dateBenefit = new DateBenefit(new Special());

        assertThat(dateBenefit.calculateDiscount(new Date(date), order)).isEqualTo(new Discount(discount));
    }
}