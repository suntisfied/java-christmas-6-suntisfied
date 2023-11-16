package christmas.order.menu;

public enum Menu {
    MUSHROOM_CREAM_SOUP(6000, Category.APPETIZER, "양송이수프"),
    TAPAS(5500, Category.APPETIZER, "타파스"),
    CAESAR_SALAD(8000, Category.APPETIZER, "시저샐러드"),
    T_BONE_STEAK(55000, Category.MAIN_DISH, "티본스테이크"),
    BARBECUE_RIBS(54000, Category.MAIN_DISH, "바비큐립"),
    SEAFOOD_PASTA(35000, Category.MAIN_DISH, "해산물파스타"),
    CHRISTMAS_PASTA(25000, Category.MAIN_DISH, "크리스마스파스타"),
    CHOCOLATE_CAKE(15000, Category.DESSERT, "초코케이크"),
    ICE_CREAM(5000, Category.DESSERT, "아이스크림"),
    ZERO_COLA(3000, Category.DRINK, "제로콜라"),
    RED_WINE(60000, Category.DRINK, "레드와인"),
    CHAMPAGNE(25000, Category.DRINK, "샴페인");

    private final String name;
    private final int price;
    private final Category category;

    Menu(int price, Category category, String name) {
        this.price = price;
        this.category = category;
        this.name = name;
    }

    public Price getPrice() {
        return new Price(price);
    }

    public Category getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public static Menu convertNameToMenu(String name) {
        for (Menu menu : Menu.values()) {
            if (menu.getName().equals(name)) {
                return menu;
            }
        }
        return null;
    }
}
