import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        List<String> fileLines = InputParser.getLinesFromLocalFile("input.txt");
        List<HotSprings> springs = new ArrayList<>();

        List<Integer> possPatterns = new ArrayList<>();

        for(String line : fileLines){
            possPatterns.add(new HotSprings(line).checkPatterns());
        }

        System.out.println(possPatterns.stream().mapToInt(e -> e).sum());
    }
}
