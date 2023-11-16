package christmas.promotion;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.converter.Converter;
import christmas.view.input.Date;
import christmas.view.input.Order;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class TotalBenefitTest {
    Date date = new Date(3);
    Order order = new Order("티본스테이크-3,양송이수프-1,아이스크림-2,레드와인-2");

    @Test
    public void calculateTotalBenefit() {
        TotalBenefit totalBenefit = new Converter().convertToTotalBenefit(new Date(10), order);

        assertThat(totalBenefit.calculateTotalBenefit())
                .isEqualTo(new Discount(31946));
    }

    @ParameterizedTest
    @CsvSource({
            "디데이, 1200",
            "평일, 4046",
            "주말, 0",
            "특별, 1000",
            "증정, 25000",
    })
    public void getDiscountByPromotionType(String promotionName, int DiscountAmount) {
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