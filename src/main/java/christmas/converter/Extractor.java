package christmas.converter;

import christmas.order.Volume;
import christmas.order.menu.Menu;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;

public class Extractor {
    HashMap<Menu, Volume> createMenus(List<String> menuNameAndVolumes) {
        HashMap<Menu, Volume> menus = new HashMap<>();

        List<String> orderedMenuNameTexts = extractNames(menuNameAndVolumes);
        List<Integer> orderedMenuVolumeTexts = extractVolumes(menuNameAndVolumes);

        for (int current = 0; current < orderedMenuNameTexts.size(); current++) {
            Menu menuName = Menu.convertNameToMenu(orderedMenuNameTexts.get(current));
            Volume volume = new Volume(orderedMenuVolumeTexts.get(current));
            menus.put(menuName, volume);
        }
        return menus;
    }

    List<String> extractNames(List<String> menuNameAndVolumes) {
        return menuNameAndVolumes.stream()
                .filter(element -> !isNumeric.test(element))
                .toList();
    }

    List<Integer> extractVolumes(List<String> menuNameAndVolumes) {
        return menuNameAndVolumes.stream()
                .filter(isNumeric)
                .map(Integer::parseInt)
                .toList();
    }

    private final Predicate<String> isNumeric = input -> {
        try {
            Double.parseDouble(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    };
}
