package christmas.promotion.bydate;

import static christmas.promotion.Defaults.SPECIAL_DISCOUNT;

import christmas.promotion.Discount;
import christmas.view.input.Date;
import christmas.view.input.Order;
import java.util.List;
import java.util.function.Predicate;

public class Special implements DateDiscount {
    @Override
    public boolean check(Date date, Order order) {
        return isValid(date, order);
    }

    @Override
    public Discount calculateDiscount(Date date, Order order) {
        int totalDiscount = 0;
        if (check(date, order)) {
            totalDiscount = SPECIAL_DISCOUNT.getNumber();
        }
        return new Discount(totalDiscount);
    }

    private boolean isValid(Date date, Order order) {
        return isSpecialDay.test(date) && isEnoughTotalOrder.test(order);
    }

    private final Predicate<Date> isSpecialDay = date -> {
        List<Integer> weekdays = Days.SPECIAL.getDays();
        return weekdays.contains(date.date());
    };
}
