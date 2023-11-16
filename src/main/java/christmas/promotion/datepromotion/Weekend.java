package christmas.promotion.datepromotion;

import static christmas.promotion.Defaults.WEEKEND_DISCOUNT_UNIT;
import static christmas.promotion.datepromotion.Days.WEEKENDS;

import christmas.order.TotalOrder;
import christmas.order.Volume;
import christmas.converter.Converter;
import christmas.order.menu.Category;
import christmas.promotion.Discount;
import christmas.view.input.Date;
import christmas.view.input.Order;
import java.util.List;
import java.util.function.Predicate;

public class Weekend extends DateDiscount {
    private Volume mainDishVolume;

    public Weekend(Order order) {
        TotalOrder totalOrder = new Converter().convertToTotalOrder(order);
        mainDishVolume = totalOrder.calculateVolumeByCategory(Category.MAIN_DISH);
    }

    @Override
    public Discount calculateDiscount(Date date, Order order) {
        int totalDiscount = 0;
        if (check(date, order)) {
            totalDiscount = WEEKEND_DISCOUNT_UNIT.getNumber() * mainDishVolume.amount();
        }
        return new Discount(totalDiscount);
    }

    @Override
    public boolean check(Date date, Order order) {
        return isWeekend.test(date) && isMainOrdered.test(order) && isEnoughTotalOrder.test(order);
    }

    private final Predicate<Date> isWeekend = date -> {
        List<Integer> weekends = WEEKENDS.getDays();
        return weekends.contains(date.number());
    };

    private final Predicate<Order> isMainOrdered = order -> mainDishVolume.amount() > 0;
}
