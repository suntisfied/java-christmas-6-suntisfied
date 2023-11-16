package christmas.promotion.datepromotion;

import static christmas.promotion.Defaults.SPECIAL_DISCOUNT;

import christmas.promotion.Discount;
import christmas.view.input.Date;
import christmas.view.input.Order;
import java.util.List;
import java.util.function.Predicate;

public class Special extends DateDiscount {
    @Override
    public Discount calculateDiscount(Date date, Order order) {
        int totalDiscount = 0;
        if (check(date, order)) {
            totalDiscount = SPECIAL_DISCOUNT.getNumber();
        }
        return new Discount(totalDiscount);
    }

    @Override
    protected boolean check(Date date, Order order) {
        return isSpecialDay.test(date) && isEnoughTotalOrder.test(order);
    }

    private final Predicate<Date> isSpecialDay = date -> {
        List<Integer> weekdays = Days.SPECIAL.getDays();
        return weekdays.contains(date.number());
    };
}
