import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        List<String> fileLines = InputParser.getLinesFromLocalFile("input.txt");

        List<HotSprings> springsList = new ArrayList<>();

        for(String line : fileLines){
            springsList.add(new HotSprings(line));
        }
        List<Long> possPatterns = new ArrayList<>();

        for(HotSprings springLine : springsList){
            possPatterns.add(HotSprings.calculateMatchingPatterns(springLine.getSprings(), springLine.getPattern()));
        }

        System.out.println(possPatterns.stream().mapToLong(e -> e).sum());
    }
}
