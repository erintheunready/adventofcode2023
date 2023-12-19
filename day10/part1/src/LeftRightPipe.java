public class LeftRightPipe extends Pipe {
    private Direction[] validDirections = {Direction.WEST, Direction.EAST};

    public Direction[] getValidDirections() {
        return validDirections;
    }

    @Override
    public char getType() {
        return '-';
    }

    public char getUnicode() {
        return '\u2501';
    }
}
