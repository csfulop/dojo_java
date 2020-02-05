package hu.fcs.dojo.wordWrapper;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class TestWordWrapper {
    private static final int LINE_LENGHT = 10;

    @Test
    void testWrapEmptyString() {
        assertWordWrap("", "");
    }

    @Test
    void testWrapNull() {
        assertWordWrap(null, "");
    }

    @Test
    void testSingleLine() {
        assertWordWrap("asdf qwer", "asdf qwer");
    }

    @Test
    void testSingleLineMaxFit() {
        assertWordWrap("asdf qwe r", "asdf qwe r");
    }

    @Test
    void testTwoLines() {
        assertWordWrap("asdf qwer 1234", "asdf qwer\n1234");
    }

    @Test
    void testLastWordJustOverflows() {
        assertWordWrap("asdf qwer r", "asdf qwer\nr");
    }

    @Test
    void testThreeLines() {
        assertWordWrap("asdf qwer 1234 aaaaaa", "asdf qwer\n1234\naaaaaa");
    }

    @Test
    void testWordLongerThanLine() {
        assertWordWrap("1234567890a", "1234567890\na");
    }

    @Test
    void testWordLongerThanTwoLines() {
        assertWordWrap("12345678901234567890a", "1234567890\n1234567890\na");
    }

    @Test
    void testLongWordInsideText() {
        assertWordWrap("asdf 1234567890123456789012345 qwer 1234", "asdf\n1234567890\n1234567890\n12345 qwer\n1234");
    }


    private void assertWordWrap(String input, String expected) {
        WordWrapper wordWrapper = new WordWrapper(LINE_LENGHT, input);
        assertThat(wordWrapper.wrap(), is(expected));
    }
}
