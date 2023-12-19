public class LPipe extends Pipe{
    private final Direction[] validDirections = {Direction.EAST, Direction.NORTH};

    public Direction[] getValidDirections() {
        return validDirections;
    }

    @Override
    public char getType() {
        return 'L';
    }

    public char getUnicode() {
        return '\u2517';
    }
}
