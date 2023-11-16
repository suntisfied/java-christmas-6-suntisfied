package christmas.converter;

public enum Delimiters {
    DELIMITER_EACH_ORDER(","),
    DELIMITER_NAME_AND_VOLUME("-"),
    ;

    private final String delimiter;

    Delimiters(String delimiter) {
        this.delimiter = delimiter;
    }

    public String getDelimiter() {
        return delimiter;
    }
}
