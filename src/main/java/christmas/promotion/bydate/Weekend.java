package christmas.promotion.bydate;

import christmas.promotion.Discount;
import christmas.view.input.Date;
import java.util.List;
import java.util.function.Predicate;

public class Weekend implements DateDiscount {
    @Override
    public boolean check(Date date) {
        return isWeekend.test(date);
    }

    @Override
    public Discount calculateDiscount(Date date) {
        return new Discount(2023);
    }

    Predicate<Date> isWeekend = date -> {
        List<Integer> weekends = Days.WEEKENDS.getDays();
        return weekends.contains(date.date());
    };
}