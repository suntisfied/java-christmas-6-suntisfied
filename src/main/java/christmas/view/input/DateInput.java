package christmas.view.input;

import static camp.nextstep.edu.missionutils.Console.readLine;
import static christmas.view.Messages.DATE_INPUT_INSTRUCTION;
import static christmas.view.Messages.INTRODUCTION;

public class DateInput extends DateInputChecker {
    public Date askDate() {
        String initialTexts = INTRODUCTION.getMessage() + System.lineSeparator() +  DATE_INPUT_INSTRUCTION.getMessage();
        System.out.println(initialTexts);

        String input;
        do {
            input = readLine();
        } while (validate(input));

        int convertedInput = Integer.parseInt(input);

        return new Date(convertedInput);
    }

    private boolean validate(String input) {
        boolean isInvalid = false;
        try {
            checkValidity(input);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            isInvalid = true;
        }
        return isInvalid;
    }
}
