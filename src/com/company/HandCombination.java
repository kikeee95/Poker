package com.company;

import java.util.ArrayList;

public class HandCombination {
    private String name;
    private ArrayList<Card> Cards;
    private int combinationId;
    private int combinationStrength;
    private String type;


    public HandCombination(ArrayList<Card> cards) {
        if (cards.size() == 5) {
            this.Cards = cards;
            this.name = calcName();
            this.combinationId = calcId();
            this.evaluateHand();

        } else {
            System.out.println("Invalid hand combination");
            System.out.println(cards.size());
        }

    }

    public HandCombination(Hand hand, ArrayList<Card> cards) {
        this(sevenCardEvaluator(hand, cards));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Card> getCards() {
        return Cards;
    }

    public void setCards(ArrayList<Card> cards) {
        Cards = cards;
    }

    public int getCombinationId() {
        return combinationId;
    }

    public void setCombinationId(int combinationId) {
        this.combinationId = combinationId;
    }

    public int getCombinationStrength() {
        return combinationStrength;
    }

    public void setCombinationStrength(int combinationStrength) {
        this.combinationStrength = combinationStrength;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private int calcId() {
        int id = 1;
        for (int i = 0; i < this.Cards.size(); i++) {
            id = id * this.Cards.get(i).getPrimeValue();
        }
        return id;
    }

    private String calcName() {
        String name = "";
        for (int i = 0; i < this.Cards.size(); i++) {
            name = name.concat(this.Cards.get(i).getName());
        }
        return name;
    }


    //Brute force handkiértékelés
    public void evaluateHand() {
        //2  3  4  5  6  7  8  9  T  J  Q  K  A
        int[] ranks = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        // s  h  d  c
        int[] suits = new int[]{0, 0, 0, 0};

        for (int i = 0; i < this.Cards.size(); i++) {
            int rankhelper = -1;
            int suithelper = -1;
            for (int j = 0; j < Constants.cards.length; j++) {
                if (this.Cards.get(i).getName().equalsIgnoreCase(Constants.cards[j])) {
                    rankhelper = j / 4;
                    suithelper = j % 4;
                    ranks[rankhelper]++;
                    suits[suithelper]++;
                }
            }

        }

        boolean royalFlush = false;
        boolean straightFlush = false;
        boolean fourOfAKind = false;
        boolean fullHouse = false;
        boolean flush = false;
        boolean straight = false;
        boolean threeOfAKind = false;
        boolean twoPair = false;
        boolean pair = false;
        boolean highcard = false;

        //Royal flush

        boolean royalstraightHelper = false;
        boolean royalflushFlushHelper = false;

        if (ranks[8] == 1 && ranks[9] == 1 && ranks[10] == 1 && ranks[11] == 1 && ranks[12] == 1) {
            royalstraightHelper = true;
        }
        if (suits[0] == 5 || suits[1] == 5 || suits[2] == 5 || suits[3] == 5) {
            royalflushFlushHelper = true;
        }

        if (royalflushFlushHelper && royalstraightHelper) {
            royalFlush = true;
            this.type = "Royal Flush";
            this.combinationStrength = 100000000;
        }

        //straightflush
        if (!royalFlush) {
            boolean flushHelper = false;
            boolean straightHelper = false;
            int straigthend = 0;

            if (suits[0] == 5 || suits[1] == 5 || suits[2] == 5 || suits[3] == 5) {
                flushHelper = true;
            }

            int consecutiveCards = 0;


            for (int i = 0; i < ranks.length; i++) {
                if (ranks[i] == 0) {
                    consecutiveCards = 0;
                } else {
                    consecutiveCards++;
                }
                if (consecutiveCards == 5) {
                    straightHelper = true;
                    straigthend = i;
                }
            }

            //check for wheel draw (A-5)
            if (ranks[12] == 1 && ranks[0] == 1 && ranks[1] == 1 && ranks[2] == 1 && ranks[3] == 1) {
                straightHelper = true;
                straigthend = 3;
            }

            if (flushHelper && straightHelper) {
                straightFlush = true;
                this.type = "Straight Flush";
                this.combinationStrength = (95000000 + straigthend);
            }


            //Four of a Kind
        }
        if (!straightFlush && !royalFlush) {
            int fourOfAKindStrength = 0;
            for (int i = 0; i < ranks.length; i++) {
                if (ranks[i] == 4) {
                    fourOfAKind = true;
                    this.type = "Four of a Kind";
                    fourOfAKindStrength = i;
                }
            }
            if (fourOfAKind) {
                this.combinationStrength = 90000000 + fourOfAKindStrength;
            }
            // full house
        }
        if (!straightFlush && !royalFlush && !fourOfAKind) {
            boolean cards3 = false;
            boolean cards2 = false;
            int drillStrength = 0;
            int pairStrength = 0;

            for (int i = 0; i < ranks.length; i++) {
                if (ranks[i] == 3) {
                    cards3 = true;
                    drillStrength = i;
                }
                if (ranks[i] == 2) {
                    cards2 = true;
                    pairStrength = i;
                }
            }

            if (cards2 && cards3) {
                fullHouse = true;
                this.type = "Full House";
                this.combinationStrength = 80000000 + (drillStrength * 100) + (pairStrength * 1);
            }
            //flush
        }
        if (!straightFlush && !royalFlush && !fourOfAKind && !fullHouse) {
            int flushStrength = 0;
            for (int i = 0; i < suits.length; i++) {
                if (suits[i] == 5) {
                    flush = true;
                    this.type = "Flush";
                }
            }

            for (int i = 0; i < ranks.length; i++) {
                if (ranks[i] == 1) {
                    flushStrength = i;
                }
            }
            if (flush) {
                flushStrength = 70000000 + flushStrength;
            }
            //Straight
        }
        if (!straightFlush && !royalFlush && !fourOfAKind && !fullHouse && !flush) {
            int consecutiveCards = 0;
            int straightStrength = 0;

            for (int i = 0; i < ranks.length; i++) {
                if (ranks[i] == 0) {
                    consecutiveCards = 0;
                } else {
                    consecutiveCards++;
                }
                if (consecutiveCards == 5) {
                    straight = true;
                    this.type = "Straight";
                    straightStrength = i;
                    this.combinationStrength = 60000000 + straightStrength;
                }
            }

            //check for wheel draw (A-5)
            if (ranks[12] == 1 && ranks[0] == 1 && ranks[1] == 1 && ranks[2] == 1 && ranks[3] == 1) {
                straight = true;
                this.type = "Straight";
                this.combinationStrength = 60000000 + 3;
            }
            //Three of a Kind
        }
        if (!straightFlush && !royalFlush && !fourOfAKind && !fullHouse && !flush && !straight) {
            int kicker1 = 0;
            int kicker2 = 0;

            for (int i = 0; i < ranks.length; i++) {
                if (ranks[i] == 3) {
                    threeOfAKind = true;
                    this.type = "Three of a Kind";
                }
            }

            if (threeOfAKind) {
                for (int i = 0; i < ranks.length; i++) {
                    if (ranks[i] == 1) {
                        if (kicker1 < 0) {
                            kicker1 = i;
                        } else {
                            kicker2 = i;
                        }
                    }
                }
                this.combinationStrength = 50000000 + (kicker2 * 100) + (kicker1);
            }

            //Two pair && pair
        }
        if (!straightFlush && !royalFlush && !fourOfAKind && !fullHouse && !flush && !straight && !threeOfAKind) {
            int pairs = 0;
            int smallerPair = -1;
            int higherPair = -1;
            int kicker1 = -1;
            int kicker2 = -1;
            int kicker3 = -1;
            int kicker4 = -1;
            int kicker5 = -1;
            for (int i = 0; i < ranks.length; i++) {
                if (ranks[i] == 2) {
                    pairs++;
                    if (smallerPair < 0) {
                        smallerPair = i;
                    } else {
                        higherPair = i;
                    }
                }
                if (ranks[i] == 1) {
                    if (kicker1 < 0) {
                        kicker1 = i;
                    } else if (kicker2 < 0) {
                        kicker2 = i;
                    } else if (kicker3 < 0) {
                        kicker3 = i;
                    } else if (kicker4 < 0) {
                        kicker4 = i;
                    } else if (kicker5 < 0) {
                        kicker5 = i;
                    }
                }

            }
            if (pairs == 2) {
                twoPair = true;
                this.type = "Two Pair";
                this.combinationStrength = 40000000 + (higherPair * 10000) + (smallerPair * 500) + kicker1;

            }
            if (pairs == 1) {
                pair = true;
                this.type = "Pair";
                this.combinationStrength = 30000000 + ((smallerPair+1) * 50000) + ((kicker3+1) * 1500) + ((kicker2+1) * 15) + kicker1;
            } else {
                highcard = true;
                this.type = "High Card";
                this.combinationStrength = 10 + ((kicker5+1) * 40000) + ((kicker4+1) * 2) + ((kicker3+1) * 190) + ((kicker2+1) * 13) + kicker1;
            }
        }
    }

    private static ArrayList<Card> sevenCardEvaluator(Hand hand, ArrayList<Card> boardCards) {

        int cardCount = 5;
        int combinationStrenght = 0;
        int temp;

        //Brute force megközelítés, végignézzük az összes lehetséges 7 lapos kombinációt és visszaadjuk a legerősebbet

        ArrayList<Card> combinations = new ArrayList<>();
        ArrayList<Card> combination = new ArrayList<>();
        ArrayList<Card> excluded = new ArrayList<>();

        for (int i = 0; i < hand.getCards().length; i++) {
            combinations.add(hand.getCards()[i]);
        }

        for (int i = 0; i < boardCards.size(); i++) {
            combinations.add(boardCards.get(i));
        }

        //7ből úgy tudunk 5 lapot kiválasztani, hogy kettőt mindig kihagyunk, 21 féle képpen választhatunk ki 2 lapot amit kihagyunk, ezt 2 ciklussal lehet kiválogatni

        ArrayList<Card> container = new ArrayList<Card>();

        for (int i = 0; i < combinations.size() - 1; i++) {
            for (int j = i + 1; j < combinations.size(); j++) {
                excluded.add(combinations.get(i));
                excluded.add(combinations.get(j));

                //kombináció létrehozása és kiértékelése a két kimaradó  lap nélkül
                if (excluded.size() == 2) {
                    combination.addAll(combinations);

                    for (int k = 0; k < excluded.size(); k++) {
                        combination.remove(excluded.get(k));
                    }
                    excluded.clear();
                }
                temp = new HandCombination(combination).getCombinationStrength();

                if (temp >= combinationStrenght) {
                    combinationStrenght = temp;
                    container.clear();
                    container.addAll(combination);
                }


                combination.clear();

            }
        }
        return container;
    }

    public static ArrayList<Hand> rangeTrimForTurn(ArrayList<Hand> range, Board board) {
        ArrayList<Hand> newRange = new ArrayList<Hand>();
        ArrayList<Card> cardContainer = new ArrayList<Card>();


        for(int i = 0; i < range.size(); i++){
            cardContainer.clear();
            cardContainer.add(board.getCards().get(0));
            cardContainer.add(board.getCards().get(1));
            cardContainer.add(board.getCards().get(2));
            cardContainer.add(range.get(i).getCards()[0]);
            cardContainer.add(range.get(i).getCards()[1]);
            if(hasDraws(range.get(i), cardContainer) || (new HandCombination(cardContainer).getCombinationStrength()) > 3000000){
                newRange.add(range.get(i));
            }
        }
        return newRange;
    }

    private static boolean hasDraws(Hand hand, ArrayList<Card> cards) {
        ArrayList<Card> cardContainer = new ArrayList<Card>();
        cardContainer.add(hand.getCards()[0]);
        cardContainer.add(hand.getCards()[1]);
        cardContainer.addAll(cards);
        boolean draw = false;


        //2  3  4  5  6  7  8  9  T  J  Q  K  A
        int[] ranks = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        // s  h  d  c
        int[] suits = new int[]{0, 0, 0, 0};

        for (int i = 0; i < cardContainer.size(); i++) {
            int rankhelper = -1;
            int suithelper = -1;
            for (int j = 0; j < Constants.cards.length; j++) {
                if (cardContainer.get(i).getName().equalsIgnoreCase(Constants.cards[j])) {
                    rankhelper = j / 4;
                    suithelper = j % 4;
                    ranks[rankhelper]++;
                    suits[suithelper]++;
                }
            }
        }
        for (int i = 0; i < suits.length; i++) {
            if (suits[i] == 4) {
                draw = true;
            }
        }
        int[] zeros = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
        for (int i = 0; i < ranks.length; i++) {

            if (ranks[i] == 0) {
                if (i < 6) {
                    zeros[0]++;
                }
                if (i >= 1 && i < 7) {
                    zeros[1]++;
                }
                if (i >= 2 && i < 8) {
                    zeros[2]++;
                }
                if (i >= 3 && i < 9) {
                    zeros[3]++;
                }
                if (i >= 4 && i < 10) {
                    zeros[4]++;
                }
                if (i >= 5 && i < 11) {
                    zeros[5]++;
                }
                if (i >= 6 && i < 12) {
                    zeros[6]++;
                }
                if (i >= 7) {
                    zeros[7]++;
                }
            }
        }
        for (int i = 0; i < zeros.length; i++) {
            if (zeros[i] <= 1){
                draw = true;
            }
        }
        return draw;
    }
}
