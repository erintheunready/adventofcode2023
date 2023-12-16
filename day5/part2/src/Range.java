public class Range {
    private final long destinationRange;
    private final long sourceRange;
    private final long rangeLength;

    public Range(long d, long s, long r){
        destinationRange = d;
        sourceRange = s;
        rangeLength = r;
    }

    public long processRange(long operand){
        if(operand >= sourceRange && operand < sourceRange + rangeLength){
            return destinationRange - sourceRange;
        }

        return 0;
    }
}
