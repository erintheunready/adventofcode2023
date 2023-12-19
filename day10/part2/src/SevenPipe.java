public class SevenPipe extends Pipe {
    private final Direction[] validDirections = {Direction.WEST, Direction.SOUTH};
    public Direction[] getValidDirections() {
        return validDirections;
    }

    @Override
    public char getType() {
        return '7';
    }

    public char getUnicode() {
        return '\u2513';
    }

    public boolean isAngle() {
        return true;
    }
}
