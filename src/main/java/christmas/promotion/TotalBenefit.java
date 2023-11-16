package christmas.promotion;

import java.util.Map;

public class TotalBenefit {
    private final Map<Promotions, Discount> totalBenefit;

    public TotalBenefit(Map<Promotions, Discount> totalBenefit) {
        this.totalBenefit = totalBenefit;
    }

    public Discount calculateTotalBenefit() {
        int totalBenefitSum = totalBenefit.values().stream().mapToInt(Discount::amount).sum();

        return new Discount(totalBenefitSum);
    }

    public Discount getDiscountByPromotion(Promotions promotions) {
        return totalBenefit.get(promotions);
    }
}
