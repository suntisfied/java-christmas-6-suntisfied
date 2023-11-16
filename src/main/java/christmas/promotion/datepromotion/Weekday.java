package christmas.promotion.datepromotion;

import static christmas.promotion.Defaults.WEEKDAY_DISCOUNT_UNIT;
import static christmas.promotion.datepromotion.Days.WEEKDAYS;

import christmas.order.TotalOrder;
import christmas.order.Volume;
import christmas.converter.Converter;
import christmas.order.menu.Category;
import christmas.promotion.Discount;
import christmas.view.input.Date;
import christmas.view.input.Order;
import java.util.List;
import java.util.function.Predicate;

public class Weekday extends DateDiscount {
    private Volume dessertVolume;

    public Weekday(Order order) {
        TotalOrder totalOrder = new Converter().convertToTotalOrder(order);
        dessertVolume = totalOrder.calculateVolumeByCategory(Category.DESSERT);
    }

    @Override
    public Discount calculateDiscount(Date date, Order order) {
        int totalDiscount = 0;
        if (check(date, order)) {
            totalDiscount = WEEKDAY_DISCOUNT_UNIT.getNumber() * dessertVolume.amount();
        }
        return new Discount(totalDiscount);
    }

    @Override
    protected boolean check(Date date, Order order) {
        return isWeekday.test(date) && isDessertOrdered.test(order) && isEnoughTotalOrder.test(order);
    }

    private final Predicate<Date> isWeekday = date -> {
        List<Integer> weekdays = WEEKDAYS.getDays();
        return weekdays.contains(date.number());
    };

    private final Predicate<Order> isDessertOrdered = order -> dessertVolume.amount() > 0;
}
