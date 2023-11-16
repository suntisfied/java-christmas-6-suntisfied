package christmas.view.output;

import static christmas.view.Messages.HEAD_PREVIEW;

import christmas.view.input.Date;
import christmas.view.input.Order;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Preview {
    public void display(Date date, Order order) {
        System.out.println(buildPreview(date, order));
    }

    public String buildPreview(Date date, Order order) {
        Map<String, String> previews = new LinkedHashMap<>(new OrderPreview().build(order));
        previews.putAll(new PromotionPreview().build(date, order));

        String previewText = previews.entrySet().stream()
                .flatMap(entry -> Stream.of(entry.getKey() + System.lineSeparator(),
                        entry.getValue() + System.lineSeparator() + System.lineSeparator()))
                .collect(Collectors.joining());

        return HEAD_PREVIEW.getMessage()
                + System.lineSeparator()
                + System.lineSeparator()
                + previewText;
    }
}
