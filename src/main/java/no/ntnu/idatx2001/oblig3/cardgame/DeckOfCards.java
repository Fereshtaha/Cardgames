package no.ntnu.idatx2001.oblig3.cardgame;

import java.util.*;
import java.util.stream.Collectors;


public class DeckOfCards {
    private ArrayList<PlayingCard> cardDeck;
    private Random random;
    private final char[] suits;
    private final int[] faceNames;

    /**
     * Lager et nytt sett med kort.
     */
    public DeckOfCards() {
        suits = new char[]{'H', 'D', 'S', 'C'};
        faceNames = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
        cardDeck = new ArrayList<>();

        for (char suit : suits) {
            for (int faceName : faceNames) {
                cardDeck.add(new PlayingCard(suit, faceName));
            }
        }
    }

    /**
     * Returnerer kortstokken
     *
     * @return deck
     */
    public ArrayList<PlayingCard> getCardDeck() {
        return cardDeck;
    }

    /**
     * Setter kortstokken
     *
     */
    public void setCardDeck(ArrayList<PlayingCard> cardDeck) {
        this.cardDeck = cardDeck;
    }

    /**
     * Deler n kort til spilleren.
     * @param n hvor mange kort som blir delt
     * @return arrayen med en liste over kort som blir gitt til spilleren.
     */
    public ArrayList<PlayingCard> dealHand(int n) {
        random = new Random();
        ArrayList<PlayingCard> randomCards = new ArrayList<>();
        ArrayList<Integer> randomIntegers = new ArrayList<>();
        Collections.shuffle(randomCards);

        if (n > cardDeck.size() || n < 0) {
            n = cardDeck.size();
        }

        while (randomIntegers.size() < n) {
            int rand = random.nextInt(cardDeck.size());
            if (!randomIntegers.contains(rand)) {
                randomIntegers.add(rand);
                randomCards.add(cardDeck.get(rand));
            }
        }

        return randomCards;
    }

    /**
     * Bruker int n fra playingcards til å lage en ny int med summen av alle kortene.
     * @param hand hånd med krot
     * @return summen av alle kortene
     */
    public int sumOfHand(Collection<PlayingCard> hand) {
        return hand.stream().
                reduce(0, (sum, playingcard) -> sum + playingcard.getFace(), Integer::sum);
    }

    /**
     * Filtrerer ut hjertekortene, og lager en liste over alle kort som er av hjerte.
     * @param cardsOnHand kortene på hånda
     * @return arraylisten
     */
    public ArrayList<PlayingCard> printHearts(ArrayList<PlayingCard> cardsOnHand) {
        ArrayList<PlayingCard> cardsOfHearts = new ArrayList<>();
        cardsOnHand.stream().
                filter(playingCard -> playingCard.getSuit() == 'H').
                forEach(cardsOfHearts::add);
        return cardsOfHearts;
    }




}