package christmas.promotion;

public enum Promotions {
    D_DAY("크리스마스 디데이 할인: "),
    WEEKDAY("평일 할인: "),
    WEEKEND("주말 할인: "),
    SPECIAL("특별 할인: "),
    FREE_GIFT("증정 이벤트: "),
    NONE("없음");

    private final String headText;

    Promotions(String text) {
        this.headText = text;
    }

    public String getText() {
        return headText;
    }
}
