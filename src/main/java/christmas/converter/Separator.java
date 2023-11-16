package christmas.converter;

import static christmas.converter.Delimiters.DELIMITER_NAME_AND_VOLUME;
import static christmas.converter.Delimiters.DELIMITER_EACH_ORDER;

import christmas.view.input.Order;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Separator {
    public List<String> createMenuNameAndVolumes(Order order) {
        List<String> eachOrders = splitToEachOrder(order.orderText());

        List<String> menuNameAndVolumes = new ArrayList<>();
        for (String currentEachOrder : eachOrders) {
            List<String> currentMenuNameAndVolume = splitToNameAndVolume(currentEachOrder);
            menuNameAndVolumes.addAll(currentMenuNameAndVolume);
        }

        return menuNameAndVolumes;
    }

    private List<String> splitToNameAndVolume(String eachOrder) {
        return Arrays.asList(eachOrder.split(DELIMITER_NAME_AND_VOLUME.getDelimiter()));
    }

    private List<String> splitToEachOrder(String orderText) {
        return Arrays.asList(orderText.split(DELIMITER_EACH_ORDER.getDelimiter()));
    }
}
