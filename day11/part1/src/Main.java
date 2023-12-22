import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception{
        List<String> smallUniverse = InputParser.getLinesFromLocalFile("input.txt");

        List<String> bigUniverse = expandUniverse(smallUniverse);
        List<int[]> galaxyCoords = new ArrayList<>();

        for(int y = 0; y < bigUniverse.size(); y++){
            String line = bigUniverse.get(y);
            for(int x = 0; x < line.length(); x++){
                if(line.charAt(x) == '#'){
                    galaxyCoords.add(new int[]{x, y});
                }
            }
        }

        List<Integer> distances = new ArrayList<>();
        while(!galaxyCoords.isEmpty()){
            int[] compareCoord = galaxyCoords.remove(0);
            for(int[] coord : galaxyCoords){
                int xDist = Math.abs(coord[0] - compareCoord[0]);
                int yDist = Math.abs(coord[1] - compareCoord[1]);
                distances.add(xDist + yDist);
            }
        }

        System.out.println("Sum: " + distances.stream().mapToInt(e -> e).sum());
    }

    public static List<String> expandUniverse(List<String> smallUniverse){

        List<Integer> expandCols = new ArrayList<>();
        List<Integer> expandRows = new ArrayList<>();

        for(int x = smallUniverse.get(0).length() - 1; x >= 0; x--){
            boolean galaxyFound = false;
            for (String s : smallUniverse) {
                if (s.charAt(x) == '#') {
                    galaxyFound = true;
                    break;
                }
            }
            if(!galaxyFound) {
                expandCols.add(x);
            }
        }
        System.out.println(expandCols.toString());

        for(int colNum : expandCols){
            smallUniverse.replaceAll(s -> new StringBuilder(s).insert(colNum, ".").toString());
        }

        String emptyRow = ".".repeat(smallUniverse.get(0).length());

        for(int y = smallUniverse.size() - 1; y >= 0; y--){
            boolean galaxyFound = false;
            String line = smallUniverse.get(y);
            for(int x = 0; x < line.length(); x++){
                if(line.charAt(x) == '#'){
                    galaxyFound = true;
                    break;
                }
            }
            if(!galaxyFound){
                smallUniverse.add(y, emptyRow);
            }
        }

        for(String line : smallUniverse){
            System.out.println(line);
        }

        return smallUniverse;
    }
}
