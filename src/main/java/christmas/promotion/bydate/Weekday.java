package christmas.promotion.bydate;

import christmas.order.VolumeCalculator;
import christmas.order.Volume;
import christmas.order.TotalOrder;
import christmas.order.menu.Category;
import christmas.order.menu.Price;
import christmas.promotion.Discount;
import christmas.view.input.Date;
import christmas.view.input.Order;
import java.util.List;
import java.util.function.Predicate;

public class Weekday implements DateDiscount {
    Volume dessertVolume;

    public Weekday(Order order) {
        VolumeCalculator volumeCalculator = new VolumeCalculator();
        dessertVolume =
                volumeCalculator.calculateOrderVolumeByCategory(order, Category.DESSERT);
    }

    @Override
    public boolean check(Date date, Order order) {
        boolean validity = false;

        TotalOrder totalOrder = new TotalOrder(order);
        Price totalOrderCost = totalOrder.calculateTotalOrderCost();

        if (isWeekday.test(date) && isDessertOrdered.test(order) && isEnoughTotalOrder.test(totalOrderCost)) {
            validity = true;
        }

        return validity;
    }

    @Override
    public Discount calculateDiscount(Date date, Order order) {
        int totalDiscount = 0;
        if (check(date, order)) {
            totalDiscount = 2023 * dessertVolume.volume();
        }
        return new Discount(totalDiscount);
    }

    Predicate<Date> isWeekday = date -> {
        List<Integer> weekdays = Days.WEEKDAYS.getDays();
        return weekdays.contains(date.date());
    };

    Predicate<Order> isDessertOrdered = order -> dessertVolume.volume() > 0;
}
