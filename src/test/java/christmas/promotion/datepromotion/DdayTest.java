package christmas.promotion.datepromotion;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.promotion.Discount;
import christmas.view.input.Date;
import christmas.view.input.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DdayTest {
    @ParameterizedTest
    @CsvSource({
            "'티본스테이크-1,레드와인-1', 1, 1000",
            "'티본스테이크-1,레드와인-1', 25, 3400",
            "'티본스테이크-1,레드와인-1', 26, 0",
            "'타파스-1,제로콜라-1', 25, 0"
    })
    public void calculateDdayDiscount(String orderList, int date, int discount) {
        Order order = new Order(orderList);
        DateBenefit dateBenefit = new DateBenefit(new Dday());

        assertThat(dateBenefit.calculateDiscount(new Date(date), order)).isEqualTo(new Discount(discount));
    }
}