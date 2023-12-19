import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

public class MapWorker {
    MapPiece currMapPiece;
    MapPiece prevMapPiece;
    int[] currCoords;
    MapPiece[][] map;
    List<int[]> loopPieces = new ArrayList<>();

    public MapWorker(MapPiece[][] map, int[] startingPoint) {
        this.map = map;
        currMapPiece = map[startingPoint[0]][startingPoint[1]];
        currCoords = startingPoint;
        prevMapPiece = null;
        loopPieces.add(currCoords);
    }

    public boolean advanceToAngle() {
        Direction[] validDirs = currMapPiece.getValidDirections();
        for (Direction dir : validDirs) {
            int[] movement = dir.getMovement();
            int[] newCoords =  { currCoords[0] + movement[0], currCoords[1] + movement[1] };
            MapPiece newMapPiece = map[newCoords[0]][newCoords[1]];
            if(newMapPiece != prevMapPiece){
                currCoords = newCoords;
                prevMapPiece = currMapPiece;
                currMapPiece = map[currCoords[0]][currCoords[1]];
                break;
            }
        }
        loopPieces.add(currCoords);

        return currMapPiece.isAngle();
    }

    public Line2D.Float buildLineSegment(){
        int[] startingCoords = currCoords;
        boolean angleFound = advanceToAngle();
        while(!angleFound){
            angleFound = advanceToAngle();
        }
        Line2D.Float newSegment = new Line2D.Float();
        if(startingCoords[0] == currCoords[0]){
            if(startingCoords[1] > currCoords[1]){
                newSegment.setLine(currCoords[0], currCoords[1], startingCoords[0], startingCoords[1]);
            } else{
                newSegment.setLine(startingCoords[0], startingCoords[1], currCoords[0], currCoords[1]);
            }
        } else{
            if(startingCoords[0] > currCoords[0]){
                newSegment.setLine(currCoords[0], currCoords[1], startingCoords[0], startingCoords[1]);
            } else{
                newSegment.setLine(startingCoords[0], startingCoords[1], currCoords[0], currCoords[1]);
            }
        }
        return newSegment;
    }

    public boolean hasArrived() {
        return currMapPiece instanceof StartingPoint;
    }

    public boolean isLoopPiece(int x, int y) {
        for(int[] coords : loopPieces){
            if(coords[0] == x && coords[1] == y){
                return true;
            }
        }
        return false;
    }
}
