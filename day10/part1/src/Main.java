import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception{
        List<String> fileLines = InputParser.getLinesFromLocalFile("input.txt");

        MapPiece[][] tempMap = new MapPiece[fileLines.get(0).length()][fileLines.size()];
        for(int i = 0; i < fileLines.size(); i++){
            tempMap[i] = parseMapLine(fileLines.get(i));
        }

        // flip it and reverse it so I'm less confused
        MapPiece[][] map = new MapPiece[tempMap[0].length][tempMap.length];
        for(int i = 0; i < tempMap.length; i++){
            for(int j = 0; j < tempMap[0].length; j++){
                map[i][j] = tempMap[j][i];
            }
        }

        int[] startingPoint = findStartingPoint(fileLines);
        System.out.println(Arrays.toString(startingPoint));

        MapWorker mapWorker = new MapWorker(map, startingPoint);

        boolean startingPointFound = false;
        while(mapWorker.getSteps() < 100000 && !startingPointFound){
            startingPointFound = mapWorker.advance();
        }
        System.out.println(map[2][0].getType());
        printMap(map);
        System.out.println(mapWorker.getSteps() / 2);
    }

    private static MapPiece[] parseMapLine(String mapLine){
        MapPiece[] linePieces = new MapPiece[mapLine.length()];
        for(int i = 0; i < mapLine.length(); i++){
            linePieces[i] = switch(mapLine.charAt(i)){
                case 'S' -> new StartingPoint();
                case 'J' -> new JPipe();
                case 'F' -> new FPipe();
                case '7' -> new SevenPipe();
                case 'L' -> new LPipe();
                case '|' -> new UpDownPipe();
                case '-' -> new LeftRightPipe();
                default -> new Ground();
            };
        }
        return linePieces;
    }

    private static int[] findStartingPoint(List<String> fileLines){
        for(int i = 0; i < fileLines.size(); i++){
            String currLine = fileLines.get(i);
            for(int j = 0; j < fileLines.get(0).length(); j++){
                if(currLine.charAt(j) == 'S'){
                    return new int[] {j, i};
                }
            }
        }
        return new int[] {0,0};
    }

    private static void printMap(MapPiece[][] map){
        for(int i = 0; i < map[0].length; i++){
            for(int j = 0; j < map.length; j++){
                System.out.print(map[j][i].getUnicode());
            }
            System.out.println();
        }
    }
}
