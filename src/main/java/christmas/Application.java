package christmas;

import camp.nextstep.edu.missionutils.Console;
import christmas.view.input.Date;
import christmas.view.input.DateInput;
import christmas.view.input.Order;
import christmas.view.input.OrderInput;
import christmas.view.output.Preview;

public class Application {
    public static void main(String[] args) {
        DateInput dateInput = new DateInput();
        OrderInput orderInput = new OrderInput();
        Preview preview = new Preview();

        Date date = dateInput.askDate();
        Order order = orderInput.askOrder();

        preview.display(date, order);

        Console.close();
    }
}
