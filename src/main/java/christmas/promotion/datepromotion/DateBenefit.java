package christmas.promotion.datepromotion;

import christmas.promotion.Discount;
import christmas.view.input.Date;
import christmas.view.input.Order;

public class DateBenefit {
    private final DateDiscount dateDiscount;

    public DateBenefit(DateDiscount dateDiscount) {
        this.dateDiscount = dateDiscount;
    }
    
    public Discount calculateDiscount(Date date, Order order) {
        return dateDiscount.calculateDiscount(date, order);
    }
}
