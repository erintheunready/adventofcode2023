public class RockGroup {
    private final int startIndex;
    private final String group;

    public RockGroup(int startIndex, String group) {
        this.startIndex = startIndex;
        this.group = group;
    }

    public int getRockWeight() {
        int numRocks = (int)(group.chars().filter(e -> e == 'O').count());
        int rockWeight = 0;
        for(int i = 0; i < numRocks; i++) {
            int test = startIndex + group.length() - i;
            System.out.println(test);
            rockWeight += test;
        }
        return rockWeight;
    }
}
