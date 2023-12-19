public enum Direction {
    WEST,
    EAST,
    NORTH,
    SOUTH;

    public int[] getMovement(){
        switch(this){
            case EAST -> {
                return new int[] {1, 0};
            }
            case WEST -> {
                return new int[] {-1, 0};
            }
            case NORTH -> {
                return new int[] {0, -1};
            }
            case SOUTH -> {
                return new int[] {0, 1};
            }
            default -> {
                return new int[] {0, 0};
            }
        }
    }
}
