public class FPipe extends Pipe {
    private final Direction[] validDirections = {Direction.EAST, Direction.SOUTH};

    public char getType(){
        return 'F';
    }

    public char getUnicode(){
        return '\u250F';
    }

    public Direction[] getValidDirections() {
        return validDirections;
    }
}
