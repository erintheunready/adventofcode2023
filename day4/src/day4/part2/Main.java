package day4.part2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {
        List<String> fileLines = InputParser.getLinesFromLocalFile("input.txt");
        List<Game> allGames = fileLines.stream().map(Game::new).toList();

        Map<Integer, Integer> winMap = getWinMap(allGames);

        int sum = sumWins(allGames);

        System.out.println("Sum: " + sum);
    }

    private static Map<Integer, Integer> getWinMap(List<Game> gameList){
        Map<Integer, Integer> winMap = new HashMap<>();
        Map<Integer,Integer> numTicketMap = new HashMap<>();
        for(int i = 0; i < gameList.size(); i++) {
            winMap.put(i, gameList.get(i).getNumWins());
            numTicketMap.put(i, 1);
        }
        for(Map.Entry<Integer, Integer> set : winMap.entrySet()){
            int index = set.getKey();
            int numWins = set.getValue();
            for(int i = 0; i < numTicketMap.get(index); i++){
                for(int j = index + 1; j <= index + numWins; j++){
                    numTicketMap.put(j, numTicketMap.get(j) + 1);
                }
            }
        }
        System.out.println(winMap);
        System.out.println(numTicketMap);
        return numTicketMap;
    }

    private static int sumWins(List<Game> gameList) {
        Map<Integer,Integer> numTicketMap = getWinMap(gameList);
        int sum = 0;
        for (int value : numTicketMap.values()) {
            sum += value;
        }
        return sum;
    }

}