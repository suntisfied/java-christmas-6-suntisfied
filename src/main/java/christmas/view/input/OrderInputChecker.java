package christmas.view.input;

import static christmas.promotion.Defaults.ORDER_VOLUME_LIMIT;

import christmas.order.TotalOrder;
import christmas.order.Volume;
import christmas.converter.Converter;
import christmas.converter.Separator;
import christmas.order.menu.Category;
import christmas.order.menu.Menu;
import christmas.view.Messages;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

public class OrderInputChecker {
    protected void checkValidity(String input) {
        boolean validInput = false;

        if (isInvalidFormat.test(new Order(input)) || isNotInMenu.test(new Order(input))) {
            throw new IllegalArgumentException(Messages.ERROR_INVALID_ORDER.getMessage());
        }

        if (!isInvalidFormat.test(new Order(input)) && !isNotInMenu.test(new Order(input))) {
            validInput = true;
        }

        if (validInput) {
            if (isOnlyDrinkOrder.test(new Order(input))
                    || isOverOrderLimit.test(new Order(input))
                    || isDuplicate.test(new Order(input))
                    || isMenuAmountZero.test(new Order(input))) {
                throw new IllegalArgumentException(Messages.ERROR_INVALID_ORDER.getMessage());
            }
        }
    }

    private final Predicate<Order> isNotInMenu = order -> {
        TotalOrder totalOrder = new Converter().convertToTotalOrder(order);
        List<Menu> orderedMenuNames = totalOrder.produceOrderedMenus();
        return orderedMenuNames.contains(null);
    };

    private final Predicate<Order> isInvalidFormat = order -> {
        Separator separator = new Separator();
        List<String> MenuAndAmounts = separator.createMenuNameAndVolumes(order);

        List<String> convertibleMembers = MenuAndAmounts.stream()
                .filter(OrderInputChecker::isConvertibleToInt)
                .toList();

        int orderedMenuNumber = MenuAndAmounts.size() - convertibleMembers.size();
        int orderedAmountNumber = convertibleMembers.size();

        return orderedMenuNumber != orderedAmountNumber;
    };

    private static boolean isConvertibleToInt(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private final Predicate<Order> isOnlyDrinkOrder = order -> {
        TotalOrder totalOrder = new Converter().convertToTotalOrder(order);
        Volume totalVolume = totalOrder.calculateTotalVolume();
        Volume drinkVolume = totalOrder.calculateVolumeByCategory(Category.DRINK);
        return totalVolume.equals(drinkVolume);
    };

    private final Predicate<Order> isOverOrderLimit = order -> {
        TotalOrder totalOrder = new Converter().convertToTotalOrder(order);
        return totalOrder.calculateTotalVolume().amount() > ORDER_VOLUME_LIMIT.getNumber();
    };

    private final Predicate<Order> isDuplicate = order -> {
        Separator separator = new Separator();
        List<String> menuNameAndAmounts = separator.createMenuNameAndVolumes(order);
        List<String> menuNames = menuNameAndAmounts.stream()
                .filter(element -> !element.matches("\\d+"))
                .toList();
        Set<String> uniqueNames = new HashSet<>(menuNames);
        return menuNames.size() != uniqueNames.size();
    };

    private final Predicate<Order> isMenuAmountZero = order -> {
        Separator separator = new Separator();
        List<String> menuNameAndAmounts = separator.createMenuNameAndVolumes(order);
        return menuNameAndAmounts.contains("0");
    };
}
