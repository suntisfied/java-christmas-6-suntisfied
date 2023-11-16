package christmas.promotion.datepromotion;

import christmas.promotion.Benefit;
import christmas.promotion.Discount;
import christmas.view.input.Date;
import christmas.view.input.Order;

public abstract class DateDiscount extends Benefit {
    public abstract Discount calculateDiscount(Date date, Order order);

    protected abstract boolean check(Date date, Order order);
}
