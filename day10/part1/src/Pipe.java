public class Pipe implements MapPiece {
    public Direction[] getValidDirections(){
        return new Direction[] {};
    }
    public boolean isPipe() {
        return true;
    }

    public char getType(){
        return 'n';
    }

    public char getUnicode() {
        return ' ';
    }
}
