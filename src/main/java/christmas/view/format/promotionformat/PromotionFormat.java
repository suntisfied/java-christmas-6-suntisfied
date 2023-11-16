package christmas.view.format.promotionformat;

import christmas.view.input.Date;
import christmas.view.input.Order;

public interface PromotionFormat {
    String format(Date date, Order order);
}
