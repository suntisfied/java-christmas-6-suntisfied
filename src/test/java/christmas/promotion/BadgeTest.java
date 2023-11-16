package christmas.promotion;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.view.input.Date;
import christmas.view.input.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BadgeTest {
    @ParameterizedTest
    @CsvSource({
            "26, '아이스크림-1', 없음",
            "26, '아이스크림-3', 스타",
            "26, '아이스크림-5', 트리",
            "26, '아이스크림-10', 산타",
    })
    public void checkCorrectBadge(int date, String order, String badgeName) {
        Badge badge = new Badge();

        assertThat(badge.determineBadge(new Date(date), new Order(order)).getName()).isEqualTo(badgeName);
    }
}