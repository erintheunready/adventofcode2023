public class InsideGround implements MapPiece{
    public boolean isPipe(){
        return true;
    }
    public Direction[] getValidDirections(){
        return new Direction[] {};
    }

    public char getType() {
        return '.';
    }

    public char getUnicode() {
        return '\u256C';
    }

    @Override
    public boolean isAngle() {
        return false;
    }
}
