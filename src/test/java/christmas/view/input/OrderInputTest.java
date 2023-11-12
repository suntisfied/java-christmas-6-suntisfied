package christmas.view.input;

import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.Console;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OrderInputTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    String mockInput =
            "해산물파스타-20,레드와인-10,초코케이크-10\n"
            + "레드와인-1\n"
            + "해산물파스타-2,레드와인-1,초코케이크-1";

    @BeforeEach
    public void setUpStreams() {
        System.setIn(new ByteArrayInputStream(mockInput.getBytes()));
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
        OrderInput orderInput = new OrderInput();

        try {
            orderInput.askOrder();
        } catch (NoSuchElementException ignored) {
        }

        assertThat(outputStream.toString()).contains("[ERROR]", "해산물파스타-2,레드와인-1,초코케이크-1");
    }
}