package christmas.promotion;

import christmas.order.menu.Menu;
import christmas.order.menu.Price;

public enum FreeGifts {
    FREE_GIFT(Menu.CHAMPAGNE.getName(), Menu.CHAMPAGNE.getPrice()),
    NONE("없음", new Price(0)),
    ;

    private final String name;
    private final Price price;

    FreeGifts(String name, Price price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Price getPrice() {
        return price;
    }
}
