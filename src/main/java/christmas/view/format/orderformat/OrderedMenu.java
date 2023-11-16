package christmas.view.format.orderformat;

import christmas.converter.Converter;
import christmas.order.TotalOrder;
import christmas.order.menu.Menu;
import christmas.view.Messages;
import christmas.view.format.BlankLineRemover;
import christmas.view.input.Order;
import java.util.List;

public class OrderedMenu implements OrderFormat {
    @Override
    public String format(Order order) {
        TotalOrder totalOrder = new Converter().convertToTotalOrder(order);
        List<Menu> orderedMenuNames = totalOrder.produceOrderedMenus();

        StringBuilder orderedMenuTextBuilder = new StringBuilder();
        for (Menu menu : orderedMenuNames) {
            orderedMenuTextBuilder.append(menu.getName())
                    .append(" ")
                    .append(totalOrder.getVolumeByMenu(menu).amount())
                    .append(Messages.UNIT_COUNT.getMessage())
                    .append(System.lineSeparator());
        }
        orderedMenuTextBuilder = new BlankLineRemover().remove(orderedMenuTextBuilder, 1);
        return orderedMenuTextBuilder.toString();
    }
}
