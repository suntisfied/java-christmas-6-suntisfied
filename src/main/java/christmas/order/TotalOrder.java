package christmas.order;

import christmas.order.menu.Category;
import christmas.order.menu.Menu;
import christmas.order.menu.Price;
import java.util.List;
import java.util.Map;

public class TotalOrder {
    private final Map<Menu, Volume> orderedMenuWithVolume;

    public TotalOrder(Map<Menu, Volume> orderedMenuWithVolume) {
        this.orderedMenuWithVolume = orderedMenuWithVolume;
    }

    public Price calculateTotalCost() {
        int totalSum = 0;
        for (Menu currentMenu : orderedMenuWithVolume.keySet()) {
            int currentMenuPrice = currentMenu.getPrice().amount();
            int currentMenuAmount = orderedMenuWithVolume.get(currentMenu).amount();

            totalSum += currentMenuPrice * currentMenuAmount;
        }

        return new Price(totalSum);
    }

    public Volume calculateTotalVolume() {
        return new Volume(orderedMenuWithVolume.values().stream()
                .mapToInt(Volume::amount).sum());
    }

    public Volume calculateVolumeByCategory(Category category) {
        int volume = 0;
        for (Menu currentMenu : orderedMenuWithVolume.keySet()) {
            if (currentMenu.getCategory().equals(category)) {
                volume += orderedMenuWithVolume.get(currentMenu).amount();
            }
        }
        return new Volume(volume);
    }

    public List<Menu> produceOrderedMenus() {
        return orderedMenuWithVolume.keySet().stream().toList();
    }

    public Volume getVolumeByMenu(Menu menu) {
        return orderedMenuWithVolume.get(menu);
    }
}
