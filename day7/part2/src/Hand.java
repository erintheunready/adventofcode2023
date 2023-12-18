import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Hand {
    private final HandType handType;
    private final String[] handCards;
    private final int wager;

    public Hand(String handString){
        String[] handSplit = handString.split(" ");

        handCards = handSplit[0].trim().split("");
        handType = parseHand(handCards);
        wager = Integer.parseInt(handSplit[1]);
    }

    private HandType parseHand(String[] handCards){
        Map<String, Integer> aggregation = new HashMap<>();
        int numJokers = 0;
        for(String card: handCards){
            if(Objects.equals(card, "J")){
                numJokers++;
            } else if(aggregation.containsKey(card)){
                aggregation.put(card, aggregation.get(card) + 1);
            } else {
                aggregation.put(card, 1);
            }
        }

        int maxGroupSize = 0;
        int numGroups = aggregation.size();
        for(Map.Entry<String, Integer> entry : aggregation.entrySet()){
            int val = entry.getValue();
            if(val > maxGroupSize){
                maxGroupSize = val;
            }
        }

        if(numJokers > 0 && numJokers < 5){
            maxGroupSize += numJokers;
        }

        if(numGroups == 5){
            return HandType.HIGH_CARD;
        }
        if(numGroups == 4){
            return HandType.ONE_PAIR;
        }
        if(numGroups < 2){
            return HandType.FIVE_OF_A_KIND;
        }

        if(numGroups == 3){
            if(maxGroupSize == 3){
                return HandType.THREE_OF_A_KIND;
            }
            return HandType.TWO_PAIR;
        }

        if(maxGroupSize == 4) {
            return HandType.FOUR_OF_A_KIND;
        }
        return HandType.FULL_HOUSE;

    }

    public int getPower() {
        return handType.getPower();
    }

    public String[] getHandCards() {
        return handCards;
    }

    public int getWager() {
        return wager;
    }

    public HandType getHandType() {
        return handType;
    }
}
