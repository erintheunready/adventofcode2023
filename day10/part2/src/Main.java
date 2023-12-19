import java.awt.geom.Line2D;
import java.util.ArrayList;
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

        int[] startingPoint = findStartingPoint(map);
        MapWorker mapWorker = new MapWorker(map, startingPoint);

        List<Line2D.Float> pipeSegments = generatePipeSegments(mapWorker);

        List<MapPiece> inLoopPieces = new ArrayList<>();
        for(int i = 0; i < map[0].length; i++){
            for(int j = 0; j < map.length; j++){
                System.out.println("x: " + i + ", y: " + j + ", " + map[i][j].getType() + " " + (mapWorker.isLoopPiece(i,j) ? "true" : "false"));

                if(!mapWorker.isLoopPiece(i,j) && isInsideLoop(pipeSegments, i, j)){

                    map[i][j] = new InsideGround();
                    inLoopPieces.add(map[i][j]);
                }
            }
        }

        printMap(map);
        System.out.println(inLoopPieces.size());
    }

    private static boolean isInsideLoop(List<Line2D.Float> pipeSegments, int x, int y) {
        int numIntersects = 0;
        for(Line2D.Float seg : pipeSegments){
            if(seg.getX1() == seg.getX2() && seg.getY1() == y && seg.getX1() > x){
                numIntersects++;
            } else {
                if (x < seg.getX1() && y > seg.getY1() && y < seg.getY2()) {
                    numIntersects++;
                }
            }
        }
        return numIntersects % 2 == 1;
    }

    private static List<Line2D.Float> generatePipeSegments(MapWorker mapWorker) {
        List<Line2D.Float> pipeSegments = new ArrayList<>();

        boolean startingPointFound = false;
        while(!startingPointFound){
            pipeSegments.add(mapWorker.buildLineSegment());
            startingPointFound = mapWorker.hasArrived();
        }

        return pipeSegments;
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

    private static int[] findStartingPoint(MapPiece[][] map){
        for(int i = 0; i < map[0].length; i++){
            for(int j = 0; j < map.length; j++){
                if(map[j][i].getType() == 'S'){
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
