package christmas.promotion.datepromotion;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.promotion.Discount;
import christmas.view.input.Date;
import christmas.view.input.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class WeekendTest {
    @ParameterizedTest
    @CsvSource({
            "'티본스테이크-1,레드와인-1', 8, 2023",
            "'티본스테이크-2,레드와인-1', 8, 4046",
            "'타파스-2,레드와인-1', 8, 0",
            "'티본스테이크-2,레드와인-1', 7, 0",
    })
    public void calculateWeekendDiscount(String orderList, int date, int discount) {
        Order order = new Order(orderList);
        DateBenefit dateBenefit = new DateBenefit(new Weekend(order));

        assertThat(dateBenefit.calculateDiscount(new Date(date), order)).isEqualTo(new Discount(discount));
    }
}