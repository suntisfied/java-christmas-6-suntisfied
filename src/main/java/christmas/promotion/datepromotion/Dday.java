package christmas.promotion.datepromotion;

import static christmas.promotion.Defaults.D_DAY;
import static christmas.promotion.Defaults.D_DAY_DISCOUNT_UNIT;
import static christmas.promotion.Defaults.INITIAL_D_DAY_DISCOUNT;

import christmas.promotion.Discount;
import christmas.view.input.Date;
import christmas.view.input.Order;
import java.util.function.Predicate;

public class Dday extends DateDiscount {
    @Override
    public Discount calculateDiscount(Date date, Order order) {
        int totalDiscount = 0;

        if (check(date, order)) {
            int initialDiscount = INITIAL_D_DAY_DISCOUNT.getNumber();
            int dDayDiscounts = date.number() - 1;
            totalDiscount = initialDiscount + (dDayDiscounts * D_DAY_DISCOUNT_UNIT.getNumber());
        }

        return new Discount(totalDiscount);
    }

    @Override
    protected boolean check(Date date, Order order) {
        return isWithinDday.test(date) && isEnoughTotalOrder.test(order);
    }

    private final Predicate<Date> isWithinDday = date -> date.number() <= D_DAY.getNumber();


}
