package christmas.view.format.promotionformat;

import christmas.view.input.Date;
import christmas.view.input.Order;

public class PromotionFormatter implements PromotionFormat {
    private final PromotionFormat promotionFormat;

    public PromotionFormatter(PromotionFormat promotionFormat) {
        this.promotionFormat = promotionFormat;
    }

    @Override
    public String format(Date date, Order order) {
        return promotionFormat.format(date, order);
    }
}
