import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception{
        List<String> fileLines = InputParser.getLinesFromLocalFile("input.txt");
        List<String> rockCols = new ArrayList<>();

        for(int i = 0; i < fileLines.get(0).length(); i++) {
            StringBuilder newCol = new StringBuilder();
            for(String line : fileLines) {
                newCol.append(line.charAt(i));
            }
            newCol.reverse();
            rockCols.add(newCol.toString());
        }

        List<RockGroup> rockGroups = new ArrayList<>();
        for(String rockCol : rockCols) {
            String[] rockBits = rockCol.split("#");
            int startIndex = 0;
            for(int i = 0; i < rockBits.length; i++) {
                    rockGroups.add(new RockGroup(startIndex, rockBits[i]));
                    startIndex += rockBits[i].length() + 1;
            }
        }

        int sum = 0;
        for(RockGroup rockGroup : rockGroups) {
            sum += rockGroup.getRockWeight();
        }

        System.out.println(sum);
    }
}
