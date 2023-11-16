package christmas.promotion;

import static christmas.promotion.Defaults.MINIMUM_ORDER_FOR_PROMOTION;

import christmas.order.TotalOrder;
import christmas.converter.Converter;
import christmas.view.input.Order;
import java.util.function.Predicate;

public abstract class Benefit {
    protected Predicate<Order> isEnoughTotalOrder = order -> {
        TotalOrder totalOrder = new Converter().convertToTotalOrder(order);
        int totalOrderAmount = totalOrder.calculateTotalCost().amount();
        return totalOrderAmount >= MINIMUM_ORDER_FOR_PROMOTION.getNumber();
    };
}
