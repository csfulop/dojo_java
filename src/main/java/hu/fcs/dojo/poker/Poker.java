package hu.fcs.dojo.poker;

import java.util.*;
import java.util.stream.Collectors;

import static hu.fcs.dojo.poker.HandRank.Poker;
import static hu.fcs.dojo.poker.HandRank.*;

public class Poker {

    private static final String RANKS = "23456789TJQKA";
    private static final String LOW_ACE_RANKS = "A23456789TJQK";

    public HandRank analyse(String hand) {
        String[] cards = getCards(hand);
        Set<Character> suites = getSuites(cards);
        List<Integer> rankCounts = getRankCounts(cards);
        boolean straight = isStraight(cards);
        boolean flush = isFlush(suites);
        if (straight && flush) {
            if (getRankOfSmallestCard(cards) == 'T') {
                return RoyalFlush;
            } else {
                return StraightFlush;
            }
        }
        if (isPoker(rankCounts)) {
            return Poker;
        }
        if (isFullHouse(rankCounts)) {
            return FullHouse;
        }
        if (flush) {
            return Flush;
        }
        if (straight) {
            return Straight;
        }
        if (isThreeOfAKind(rankCounts)) {
            return Three;
        }
        if (isTwoPair(rankCounts)) {
            return TwoPair;
        }
        if (isPair(rankCounts)) {
            return Pair;
        }
        return HighCard;
    }

    private char getRankOfSmallestCard(String[] cards) {
        return getRank(cards[0]);
    }

    private String[] getCards(String hand) {
        if (hand == null) {
            throw new IllegalArgumentException("hand is null");
        }
        String[] cards = hand.split("\\s+");
        if (cards.length != 5) {
            throw new IllegalArgumentException("give 5 cards");
        }
        for (String card : cards) {
            if (card.length() != 2) {
                throw new IllegalArgumentException("give 2 character cards: " + card);
            }
        }
        Arrays.sort(cards, Comparator.comparing((card) -> RANKS.indexOf(getRank(card))));
        return cards;
    }

    private Set<Character> getSuites(String[] cards) {
        Set<Character> suites = new HashSet<>();
        for (String card : cards) {
            char suite = getSuite(card);
            suites.add(suite);
        }
        return suites;
    }

    List<Integer> getRankCounts(String[] cards) {
        Map<Character, Integer> ranks = new HashMap<>();
        for (String card : cards) {
            char rank = getRank(card);
            int count = ranks.containsKey(rank) ? ranks.get(rank) + 1 : 1;
            ranks.put(rank, count);
        }
        List<Integer> rankCounts = new ArrayList<>(ranks.values());
        Collections.sort(rankCounts);
        return rankCounts;
    }

    boolean isStraight(String[] cards) {
        List<Integer> rankValues = Arrays.stream(cards).
                map((card) -> RANKS.indexOf(getRank(card))).
                sorted().
                collect(Collectors.toList());
        if (rankValues.get(rankValues.size() - 1) - rankValues.get(0) == 4) {
            return true;
        }
        rankValues = Arrays.stream(cards).
                map((card) -> LOW_ACE_RANKS.indexOf(getRank(card))).
                sorted().
                collect(Collectors.toList());
        return rankValues.get(rankValues.size() - 1) - rankValues.get(0) == 4;
    }

    private boolean isFlush(Set<Character> suites) {
        return suites.size() == 1;
    }

    private boolean isPoker(List<Integer> rankCounts) {
        return rankCounts.equals(Arrays.asList(1, 4));
    }

    private boolean isFullHouse(List<Integer> rankCounts) {
        return rankCounts.equals(Arrays.asList(2, 3));
    }

    private boolean isThreeOfAKind(List<Integer> rankCounts) {
        return rankCounts.equals(Arrays.asList(1, 1, 3));
    }

    private boolean isTwoPair(List<Integer> rankCounts) {
        return rankCounts.equals(Arrays.asList(1, 2, 2));
    }

    private boolean isPair(List<Integer> rankCounts) {
        return rankCounts.equals(Arrays.asList(1, 1, 1, 2));
    }

    private char getSuite(String card) {
        return card.charAt(0);
    }

    private char getRank(String card) {
        return card.charAt(1);
    }
}
