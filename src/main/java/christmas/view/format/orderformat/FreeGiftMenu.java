package christmas.view.format.orderformat;

import christmas.order.Volume;
import christmas.promotion.FreeGift;
import christmas.promotion.FreeGifts;
import christmas.view.Messages;
import christmas.view.input.Order;
import java.util.Map;

public class FreeGiftMenu implements OrderFormat {
    @Override
    public String format(Order order) {
        FreeGift freeGift = new FreeGift();
        Map<FreeGifts, Volume> freeGiftWithVolume = freeGift.determineGift(order);

        String freeGiftText = FreeGifts.NONE.getName();
        if (!freeGiftWithVolume.containsKey(FreeGifts.NONE)) {
            StringBuilder freeGiftTextBuilder = new StringBuilder();
            for (FreeGifts currentFreeGift : freeGiftWithVolume.keySet()) {
                freeGiftTextBuilder.append(currentFreeGift.getName())
                        .append(" ")
                        .append(freeGiftWithVolume.get(currentFreeGift).amount())
                        .append(Messages.UNIT_COUNT.getMessage());
            }
            freeGiftText = freeGiftTextBuilder.toString();
        }
        return freeGiftText;
    }
}
