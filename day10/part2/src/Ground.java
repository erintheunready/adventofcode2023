public class Ground implements MapPiece{
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
        return ' ';
    }

    @Override
    public boolean isAngle() {
        return false;
    }
}
