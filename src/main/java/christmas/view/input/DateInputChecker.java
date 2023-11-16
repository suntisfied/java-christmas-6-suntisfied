package christmas.view.input;

import static christmas.promotion.Defaults.INITIAL_DAY_OF_MONTH;
import static christmas.promotion.Defaults.LAST_DAY_OF_MONTH;

import christmas.view.Messages;
import java.util.function.Predicate;

public class DateInputChecker {
    protected void checkValidity(String input) {
        if (isNonPositiveInteger.test(input) || isInvalidDate.test(input)) {
            throw new IllegalArgumentException(Messages.ERROR_INVALID_DATE.getMessage());
        }
    }

    private final Predicate<String> isInvalidDate = input -> {
        int date = Integer.parseInt(input);
        return date < INITIAL_DAY_OF_MONTH.getNumber() || date > LAST_DAY_OF_MONTH.getNumber();
    };

    private final Predicate<String> isNonPositiveInteger = input -> {
        try {
            return Integer.parseInt(input) < 0;
        } catch (NumberFormatException e) {
            return true;
        }
    };
}
