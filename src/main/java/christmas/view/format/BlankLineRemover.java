package christmas.view.format;

public class BlankLineRemover {
    public StringBuilder remove(StringBuilder stringBuilder, int amount) {
        int separatorLength = System.lineSeparator().length();
        if (!stringBuilder.isEmpty()) {
            stringBuilder.delete(stringBuilder.length() - (separatorLength * amount),
                    stringBuilder.length());
        }
        return stringBuilder;
    }
}
