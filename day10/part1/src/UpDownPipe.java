public class UpDownPipe extends Pipe{
    private Direction[] validDirections = {Direction.NORTH, Direction.SOUTH};

    public Direction[] getValidDirections() {
        return validDirections;
    }

    public char getType() {
        return '|';
    }

    public char getUnicode() {
        return '\u2503';
    }
}
