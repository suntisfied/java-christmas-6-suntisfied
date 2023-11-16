package christmas.promotion;

public enum Badges {
    NONE(0, "없음"),
    STAR(5000, "스타"),
    TREE(10000, "트리"),
    SANTA(20000, "산타");

    private final int requiredOrder;
    private final String name;

    Badges(int requiredOrder, String name) {
        this.requiredOrder = requiredOrder;
        this.name = name;
    }

    public int getRequiredOrder() {
        return requiredOrder;
    }

    public String getName() {
        return name;
    }
}
