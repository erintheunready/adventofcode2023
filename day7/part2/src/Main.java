import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    private static Map<String, Integer> cardMap;

    public static void main(String[] args) throws Exception{
        cardMap = new HashMap<>();
        for(int i = 2; i < 10; i++){
            cardMap.put(Integer.toString(i), i - 2);
        }
        cardMap.put("T", 9);
        cardMap.put("J", 10);
        cardMap.put("Q", 11);
        cardMap.put("K", 12);
        cardMap.put("A", 13);

        List<String> fileLines = InputParser.getLinesFromLocalFile("input.txt");
        List<Hand> handList = new ArrayList<>();

        for(String line : fileLines){
            handList.add(new Hand(line));
        }

        // Sort the list
        quickSortHand(handList, 0, handList.size() - 1);

        int sum = 0;
        for(int i = 0; i < handList.size(); i++){
            sum +=handList.get(i).getWager() * (i+1);
        }

        System.out.println("Sum: " + sum);
    }

    // Quicksort implement
    private static void quickSortHand(List<Hand> handList, int left, int right){
        if(left >= right) {
            return;
        }

        int q = partition(handList, left, right);
        quickSortHand(handList, left, q-1);
        quickSortHand(handList, q + 1, right);
    }

    private static int partition(List<Hand> array, int first, int last) {
        Hand key = array.get(last);
        int smaller = first - 1;
        for (int test = first; test < last; test++) {
            if(handCompare(array.get(test), key)){
                smaller++;
                exchange(smaller, test, array);
            }
        }
        smaller++;
        exchange(last, smaller, array);
        return smaller;
    }

    private static void exchange(int j, int i, List<Hand> array){
        Hand temp = array.get(j);
        array.set(j, array.get(i));
        array.set(i, temp);
    }

    private static boolean handCompare(Hand a, Hand b){
        if(a.getPower() > b.getPower()){
            return false;
        } else if(a.getPower() < b.getPower()){
            return true;
        } else{
            String[] aCards = a.getHandCards();
            String[] bCards = b.getHandCards();
            for(int i = 0; i < aCards.length; i++){
                int compare = compareCards(aCards[i], bCards[i]);
                if(compare > 0){
                    return false;
                }
                if(compare < 0){
                    return true;
                }
            }
        }
        return false;
    }

    private static int compareCards(String a, String b){
        return cardMap.get(a) - cardMap.get(b);
    }
}
