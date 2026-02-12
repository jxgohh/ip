package chaird;

import chaird.exception.ChairdException;
import chaird.parser.Command;
import chaird.parser.Parser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    @Test
    public void parseLine_taskCommand_returnsCorrectCommand() throws ChairdException {
        Command cmd = Parser.parseLine("todo read book");
        assertEquals("todo", cmd.getAction());
        assertEquals("read book", cmd.getDesc());

        cmd = Parser.parseLine("event restaurant date /from 2025-02-14 1200");
        assertEquals("event", cmd.getAction());
        assertEquals("restaurant date", cmd.getDesc());
        assertEquals("2025-02-14 1200", cmd.getDate());

        cmd = Parser.parseLine("mark 3");
        assertEquals("mark", cmd.getAction());
        assertEquals(3, cmd.getIndex());
    }

    @Test
    public void parseLine_invalidIndex_unmarkThrowsException() {
        assertThrows(ChairdException.class,
                () -> Parser.parseLine("mark two"));
    }

    @Test
    public void parseLine_invalidTime_throwsException() {
        assertThrows(ChairdException.class,
                () -> Parser.parseLine("deadline read book /by     "));
    }

    @Test
    public void parseLine_taskNoDescription_throwsException() {
        assertThrows(ChairdException.class,
                () -> Parser.parseLine("todo"));
    }

    @Test
    public void parseLine_invalidCommand_throwsException() {
        assertThrows(ChairdException.class,
                () -> Parser.parseLine("12345"));
    }
}
