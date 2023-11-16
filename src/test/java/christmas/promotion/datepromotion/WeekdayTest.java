package christmas.promotion.datepromotion;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.promotion.Discount;
import christmas.view.input.Date;
import christmas.view.input.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class WeekdayTest {
    @ParameterizedTest
    @CsvSource({
            "'타파스-2,아이스크림-1', 7, 2023",
            "'타파스-2,아이스크림-3', 7, 6069",
            "'아이스크림-1', 7, 0",
            "'타파스-2,아이스크림-3', 8, 0",
    })
    public void calculateWeekdayDiscount(String orderList, int date, int discount) {
        Order order = new Order(orderList);
        DateBenefit dateBenefit = new DateBenefit(new Weekday(order));

        assertThat(dateBenefit.calculateDiscount(new Date(date), order)).isEqualTo(new Discount(discount));
    }
}