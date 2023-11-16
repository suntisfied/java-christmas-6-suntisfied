package christmas.converter;

import static christmas.promotion.Promotions.D_DAY;
import static christmas.promotion.Promotions.FREE_GIFT;
import static christmas.promotion.Promotions.SPECIAL;
import static christmas.promotion.Promotions.WEEKDAY;
import static christmas.promotion.Promotions.WEEKEND;

import christmas.order.TotalOrder;
import christmas.order.Volume;
import christmas.order.menu.Menu;
import christmas.promotion.Discount;
import christmas.promotion.Promotions;
import christmas.promotion.TotalBenefit;
import christmas.promotion.datepromotion.DateBenefit;
import christmas.promotion.datepromotion.DateDiscount;
import christmas.promotion.datepromotion.Dday;
import christmas.promotion.datepromotion.Special;
import christmas.promotion.datepromotion.Weekday;
import christmas.promotion.datepromotion.Weekend;
import christmas.promotion.FreeGift;
import christmas.promotion.FreeGifts;
import christmas.view.input.Date;
import christmas.view.input.Order;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Converter {
    public TotalOrder convertToTotalOrder(Order order) {
        List<String> menuNameAndVolumes = new Separator().createMenuNameAndVolumes(order);
        Map<Menu, Volume> orderedMenuTotal = new Extractor().createMenus(menuNameAndVolumes);

        return new TotalOrder(orderedMenuTotal);
    }

    public TotalBenefit convertToTotalBenefit(Date date, Order order) {
        Map<Promotions, Discount> benefits = createPromotionBenefits(date, order);
        benefits.putAll(createFreeGiftBenefits(order));

        return new TotalBenefit(benefits);
    }

    private Map<Promotions, Discount> createPromotionBenefits(Date date, Order order) {
        List<Promotions> promotions = Arrays.asList(D_DAY, WEEKDAY, WEEKEND, SPECIAL);

        List<DateDiscount> dateDiscounts =
                Arrays.asList(new Dday(), new Weekday(order), new Weekend(order), new Special());

        Map<Promotions, Discount> promotionBenefits = new HashMap<>();
        for (int current = 0; current < promotions.size(); current++) {
            DateBenefit dateBenefit = new DateBenefit(dateDiscounts.get(current));
            promotionBenefits.put(promotions.get(current), dateBenefit.calculateDiscount(date, order));
        }
        return promotionBenefits;
    }

    private Map<Promotions, Discount> createFreeGiftBenefits(Order order) {
        FreeGift freeGift = new FreeGift();
        Map<FreeGifts, Volume> freeGiftWithVolume = freeGift.determineGift(order);
        Map<Promotions, Discount> freeGiftBenefits = new HashMap<>();
        for (FreeGifts currentFreeGift : freeGiftWithVolume.keySet()) {
            freeGiftBenefits.put(FREE_GIFT, new Discount(currentFreeGift.getPrice().amount()));
        }
        return freeGiftBenefits;
    }
}
