public enum HandType {
    FIVE_OF_A_KIND(6),
    FOUR_OF_A_KIND(5),
    FULL_HOUSE(4),
    THREE_OF_A_KIND(3),
    TWO_PAIR(2),
    ONE_PAIR(1),
    HIGH_CARD(0);

    private final int power;
    HandType(final int power) {
        this.power = power;
    }

    public int getPower() {
        return power;
    }
}
