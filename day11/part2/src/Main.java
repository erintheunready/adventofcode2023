import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception{
        List<String> smallUniverse = InputParser.getLinesFromLocalFile("input.txt");
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
                expandRows.add(y);
            }
        }

        List<int[]> galaxyCoords = new ArrayList<>();

        for(int y = 0; y < smallUniverse.size(); y++){
            String line = smallUniverse.get(y);
            for(int x = 0; x < line.length(); x++){
                if(line.charAt(x) == '#'){
                    galaxyCoords.add(new int[]{x, y});
                }
            }
        }

        List<Long> distances = new ArrayList<>();
        while(!galaxyCoords.isEmpty()){
            int[] compareCoord = galaxyCoords.remove(0);
            for(int[] coord : galaxyCoords){
                int expand = 0;
                int largerX = 0;
                int smallerX = 0;
                int smallerY = 0;
                int largerY = 0;

                if(coord[0] > compareCoord[0]){
                    largerX = coord[0];
                    smallerX = compareCoord[0];
                } else{
                    smallerX = coord[0];
                    largerX = compareCoord[0];
                }
                if(coord[1] > compareCoord[1]){
                    largerY = coord[1];
                    smallerY = compareCoord[1];
                } else{
                    smallerY = coord[1];
                    largerY = compareCoord[1];
                }


                for(int col : expandCols){
                    if(col > smallerX && col < largerX){
                        expand++;
                    }
                }
                for(int row : expandRows){
                    if(row > smallerY && row < largerY){
                        expand++;
                    }
                }
                long xDist = Math.abs(coord[0] - compareCoord[0]);
                long yDist = Math.abs(coord[1] - compareCoord[1]);
                System.out.println(Arrays.toString(compareCoord) + " " + Arrays.toString(coord) + " " + xDist + " " + yDist);
                distances.add(xDist + yDist + (expand * 999999L));
            }
        }

        System.out.println("Sum: " + distances.stream().mapToLong(e -> e).sum());
    }
}
