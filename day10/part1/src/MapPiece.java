public interface MapPiece {
    public boolean isPipe();
    public Direction[] getValidDirections();
    public char getType();

    public char getUnicode();
}
