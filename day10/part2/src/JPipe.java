public class JPipe extends Pipe {
    private final Direction[] validDirections = {Direction.WEST, Direction.NORTH};

    public Direction[] getValidDirections() {
        return validDirections;
    }

    @Override
    public char getType() {
        return 'J';
    }

    public char getUnicode() {
        return '\u251B';
    }

    public boolean isAngle() {
        return true;
    }
}
