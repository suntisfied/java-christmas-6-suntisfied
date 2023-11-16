package christmas.view.output;

import static christmas.view.Messages.HEAD_FREE_GIFT;
import static christmas.view.Messages.HEAD_ORDER_AMOUNT;
import static christmas.view.Messages.HEAD_ORDER_LIST;

import christmas.view.Messages;
import christmas.view.input.Order;
import christmas.view.format.orderformat.FreeGiftMenu;
import christmas.view.format.orderformat.InitialTotalCost;
import christmas.view.format.orderformat.OrderFormat;
import christmas.view.format.orderformat.OrderFormatter;
import christmas.view.format.orderformat.OrderedMenu;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class OrderPreview {
    Map<String, String> build(Order order) {
        Map<String, String> orderPreviews = new LinkedHashMap<>();

        List<Messages> orderHeadTexts =
                Arrays.asList(HEAD_ORDER_LIST, HEAD_ORDER_AMOUNT, HEAD_FREE_GIFT);
        List<OrderFormat> orders =
                Arrays.asList(new OrderedMenu(),
                        new InitialTotalCost(),
                        new FreeGiftMenu());

        for (int i = 0; i < orderHeadTexts.size(); i++) {
            OrderFormatter orderFormatter = new OrderFormatter(orders.get(i));
            orderPreviews.put(orderHeadTexts.get(i).getMessage(), orderFormatter.format(order));
        }
        return orderPreviews;
    }
}
