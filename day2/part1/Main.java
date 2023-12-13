import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main (String args[]) throws Exception {
        List<GameResult> gameResults = new ArrayList<>();
        InputParser.getLinesFromLocalFile("input.txt").forEach((a) -> {
            gameResults.add(new GameResult(a));
        });
        int sum = gameResults.stream().mapToInt(res -> res.getIdIfValid(12,14,13)).sum();
        System.out.println("Sum: " + sum);
    }
}
