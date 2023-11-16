package christmas.converter;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.order.TotalOrder;
import christmas.order.Volume;
import christmas.order.menu.Menu;
import christmas.promotion.Discount;
import christmas.promotion.Promotions;
import christmas.promotion.TotalBenefit;
import christmas.view.input.Date;
import christmas.view.input.Order;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ConverterTest {
    Date date = new Date(3);
    Order order = new Order("해산물파스타-2,레드와인-1,초코케이크-2");

    @ParameterizedTest
    @CsvSource({
            "해산물파스타, 2",
            "레드와인, 1",
            "초코케이크, 2",
    })
    public void convertToTotalOrder(String menuName, int orderAmount) {
        TotalOrder totalOrder = new Converter().convertToTotalOrder(order);

        assertThat(totalOrder.getVolumeByMenu(Menu.convertNameToMenu(menuName))).isEqualTo(new Volume(orderAmount));
    }

    @ParameterizedTest
    @CsvSource({
            "디데이, 1200",
            "평일, 4046",
            "주말, 0",
            "특별, 1000",
            "증정, 25000",
    })
    public void convertToTotalBenefit(String promotionName, int DiscountAmount) {
        TotalBenefit totalBenefit = new Converter().convertToTotalBenefit(date, order);

        Map<String, Promotions> promotionNames = new HashMap<>();
        promotionNames.put("디데이", Promotions.D_DAY);
        promotionNames.put("평일", Promotions.WEEKDAY);
        promotionNames.put("주말", Promotions.WEEKEND);
        promotionNames.put("특별", Promotions.SPECIAL);
        promotionNames.put("증정", Promotions.FREE_GIFT);

        Promotions convertedPromotionName = promotionNames.get(promotionName);

        assertThat(totalBenefit.getDiscountByPromotion(convertedPromotionName))
                .isEqualTo(new Discount(DiscountAmount));
    }
}