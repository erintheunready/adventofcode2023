public class StartingPoint implements MapPiece{
    private final Direction[] validDirections = {Direction.EAST, Direction.NORTH, Direction.SOUTH, Direction.WEST};

    public Direction[] getValidDirections() {
        return validDirections;
    }

    public boolean isPipe() {
        return false;
    }

    @Override
    public char getType() {
        return 'S';
    }

    public char getUnicode() {
        return '\u2573';
    }
}
