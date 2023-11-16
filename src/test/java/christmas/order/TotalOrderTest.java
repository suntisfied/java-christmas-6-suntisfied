package christmas.order;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.converter.Converter;
import christmas.order.menu.Category;
import christmas.order.menu.Menu;
import christmas.order.menu.Price;
import christmas.view.input.Order;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class TotalOrderTest {
    Order order = new Order("티본스테이크-2,양송이수프-1,시저샐러드-1,아이스크림-1,레드와인-2");
    TotalOrder totalOrder = new Converter().convertToTotalOrder(order);

    @Test
    public void calculateInitialTotalCost() {
        assertThat(totalOrder.calculateTotalCost()).isEqualTo(new Price(249000));
    }

    @Test
    public void calculateTotalVolume() {
        assertThat(totalOrder.calculateTotalVolume()).isEqualTo(new Volume(7));
    }

    @ParameterizedTest
    @CsvSource({
            "에피타이저, 2",
            "메인, 2",
            "디저트, 1",
            "음료, 2",
    })
    public void calculateVolumeByOrderCategory(String categoryName, int orderAmount) {
        Map<String, Category> categoryNames = new HashMap<>();
        categoryNames.put("에피타이저", Category.APPETIZER);
        categoryNames.put("메인", Category.MAIN_DISH);
        categoryNames.put("디저트", Category.DESSERT);
        categoryNames.put("음료", Category.DRINK);

        assertThat(totalOrder.calculateVolumeByCategory(categoryNames.get(categoryName)))
                .isEqualTo(new Volume(orderAmount));
    }

    @Test
    public void produceOrderedItemList() {
        assertThat(totalOrder.produceOrderedMenus())
                .containsExactlyInAnyOrderElementsOf(Arrays.asList(
                        Menu.T_BONE_STEAK,
                        Menu.MUSHROOM_CREAM_SOUP,
                        Menu.CAESAR_SALAD,
                        Menu.ICE_CREAM,
                        Menu.RED_WINE));
    }

    @ParameterizedTest
    @CsvSource({
            "티본스테이크, 2",
            "양송이수프, 1",
            "시저샐러드, 1",
            "아이스크림, 1",
            "레드와인, 2",
    })
    public void getVolumeByMenuName(String menuName, int orderAmount) {
        assertThat(totalOrder.getVolumeByMenu(Menu.convertNameToMenu(menuName))).isEqualTo(new Volume(orderAmount));
    }
}