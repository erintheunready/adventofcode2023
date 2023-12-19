import java.util.Arrays;
import java.util.Map;

public class MapWorker {
    MapPiece currMapPiece;
    MapPiece prevMapPiece;
    int[] currCoords;
    int steps;
    MapPiece[][] map;

    public MapWorker(MapPiece[][] map, int[] startingPoint) {
        this.map = map;
        steps = 0;
        currMapPiece = map[startingPoint[0]][startingPoint[1]];
        currCoords = startingPoint;
        prevMapPiece = null;
    }

    public boolean advance() {
        steps++;
        Direction[] validDirs = currMapPiece.getValidDirections();
        System.out.println(Arrays.toString(validDirs));
        for (Direction dir : validDirs) {
            int[] movement = dir.getMovement();
            int[] newCoords =  { currCoords[0] + movement[0], currCoords[1] + movement[1] };
            MapPiece newMapPiece = map[newCoords[0]][newCoords[1]];
            if(newMapPiece != prevMapPiece){
                System.out.println(dir);
                System.out.println(Arrays.toString(movement));
                currCoords = newCoords;
                prevMapPiece = currMapPiece;
                currMapPiece = map[currCoords[0]][currCoords[1]];
                System.out.println(Arrays.toString(currCoords));
                System.out.println(currMapPiece.getType());
                break;
            }
        }

        return currMapPiece instanceof StartingPoint;
    }

    public int getSteps(){
        return steps;
    }
}
