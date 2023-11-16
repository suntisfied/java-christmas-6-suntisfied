package christmas.view.input;

import static christmas.view.Messages.ERROR_INVALID_DATE;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.Console;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DateInputTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outputStream));

    }

    @AfterEach
    public void restoreStreams() {
        System.setIn(System.in);
        System.setOut(System.out);
        Console.close();
    }

    @Test
    public void askInputUntilCorrect() {
        String mockInput = "date\n3";
        System.setIn(new ByteArrayInputStream(mockInput.getBytes()));

        DateInput dateInput = new DateInput();

        Date date = null;
        try {
            date = dateInput.askDate();
        } catch (NoSuchElementException ignored) {
        }

        assertThat(outputStream.toString()).contains(ERROR_INVALID_DATE.getMessage());
        assertThat(date).isEqualTo(new Date(3));
    }

    @ParameterizedTest
    @CsvSource({
            "삼일",
            "three",
            "0",
            "32",
            "-1",
            "1.1",
            "'1,2'",
            "'1,1'",
    })
    public void checkInvalidDate(String mockInput) {
        System.setIn(new ByteArrayInputStream(mockInput.getBytes()));

        DateInput dateInput = new DateInput();

        try {
            dateInput.askDate();
        } catch (NoSuchElementException ignored) {
        }

        assertThat(outputStream.toString()).contains(ERROR_INVALID_DATE.getMessage());
    }
}