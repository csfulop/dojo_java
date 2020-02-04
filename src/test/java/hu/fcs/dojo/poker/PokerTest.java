package hu.fcs.dojo.poker;

import org.junit.jupiter.api.Test;

import static hu.fcs.dojo.poker.HandRank.Poker;
import static hu.fcs.dojo.poker.HandRank.*;
import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PokerTest {
    private Poker poker = new Poker();

    @Test
    void testTooFewCardsInHand() {
        assertThrows(IllegalArgumentException.class, () -> {
            poker.analyse("");
        });
    }

    @Test
    void testTooMuchCardsInHand() {
        assertThrows(IllegalArgumentException.class, () -> {
            poker.analyse("1 2 3 4 5 6");
        });
    }

    @Test
    void testNullHand() {
        assertThrows(IllegalArgumentException.class, () -> {
            poker.analyse(null);
        });
    }

    @Test
    void testInvalidCard() {
        assertThrows(IllegalArgumentException.class, () -> {
            poker.analyse("H2 D3 S4 C5 H71");
        });
    }

    @Test
    void testHighCard() {
        assertThat(poker.analyse("H2 D3 S4 C5 H7"), is(HighCard));
    }

    @Test
    void testPair() {
        assertThat(poker.analyse("H2 D2 S4 C5 H7"), is(Pair));
    }

    @Test
    void testTwoPair() {
        assertThat(poker.analyse("H2 D2 S4 C4 H7"), is(TwoPair));
    }

    @Test
    void testThreeOfAKind() {
        assertThat(poker.analyse("H2 D2 S2 C5 H7"), is(Three));
    }

    @Test
    void testStraight() {
        assertThat(poker.analyse("H2 D3 S4 C5 H6"), is(Straight));
        assertThat(poker.analyse("H2 D3 S4 C5 HA"), is(Straight));
    }

    @Test
    void testFlush() {
        assertThat(poker.analyse("H2 H2 H4 H5 H7"), is(Flush));
    }

    @Test
    void testFullHouse() {
        assertThat(poker.analyse("H2 D2 S2 C3 H3"), is(FullHouse));
    }

    @Test
    void testPoker() {
        assertThat(poker.analyse("H2 D2 S2 C2 H3"), is(Poker));
    }

    @Test
    void testStraightFlush() {
        assertThat(poker.analyse("H2 H3 H4 H5 H6"), is(StraightFlush));
        assertThat(poker.analyse("S2 S3 S4 S5 SA"), is(StraightFlush));
    }

    @Test
    void testRoyalFlush() {
        assertThat(poker.analyse("HA HJ HQ HK HT"), is(RoyalFlush));
    }

    @Test
    void testRankCounts() {
        assertThat(poker.getRankCounts(new String[]{"H2", "D2", "S2", "C5", "H7"}), is(asList(1, 1, 3)));
    }

    @Test
    void testIsStraight() {
        assertThat(poker.isStraight(new String[]{"H2", "D3", "S4", "C5", "H6"}), is(true));
        assertThat(poker.isStraight(new String[]{"H2", "D3", "S4", "C5", "HA"}), is(true));
    }
}
