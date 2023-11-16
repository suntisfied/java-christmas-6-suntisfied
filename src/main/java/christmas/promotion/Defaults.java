package christmas.promotion;

public enum Defaults {
    MINIMUM_ORDER_FOR_PROMOTION(10000),
    INITIAL_D_DAY_DISCOUNT(1000),
    D_DAY_DISCOUNT_UNIT(100),
    D_DAY(25),
    WEEKDAY_DISCOUNT_UNIT(2023),
    WEEKEND_DISCOUNT_UNIT(2023),
    SPECIAL_DISCOUNT(1000),
    MINIMUM_ORDER_FOR_FREE_GIFT(120000),
    NUMBER_OF_FREE_GIFT(1),
    INITIAL_DAY_OF_MONTH(1),
    LAST_DAY_OF_MONTH(31),
    ORDER_VOLUME_LIMIT(20),
    ;

    private final int number;

    Defaults(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
