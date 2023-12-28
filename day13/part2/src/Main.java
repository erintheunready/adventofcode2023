import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        List<String> fileLines = InputParser.getLinesFromLocalFile("input.txt");
        List<Pattern> patterns = new ArrayList<>();

        List<String> currLines = new ArrayList<>();
        for(String line : fileLines) {
            if(!line.trim().isEmpty()){
                currLines.add(line.trim());
            } else {
                if(!currLines.isEmpty()){
                    patterns.add(new Pattern(currLines));
                    currLines.clear();
                }
            }
        }
        if(!currLines.isEmpty()){
            patterns.add(new Pattern(currLines));
            currLines.clear();
        }

        int sum = patterns.stream().mapToInt(Pattern::findReflectionQuantity).sum();

        System.out.println("Sum: " + sum);
    }
}
