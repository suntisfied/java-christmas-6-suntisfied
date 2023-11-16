package christmas.view.format.orderformat;

import christmas.view.input.Order;

public class OrderFormatter implements OrderFormat {
    private final OrderFormat orderFormat;

    public OrderFormatter(OrderFormat orderFormat) {
        this.orderFormat = orderFormat;
    }

    @Override
    public String format(Order order) {
        return orderFormat.format(order);
    }
}
