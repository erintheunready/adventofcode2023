import java.util.ArrayList;
import java.util.List;

public class MappingRanges {
    List<Range> ranges = new ArrayList<>();

    public MappingRanges(List<String> rangeStrings){
        for(String a : rangeStrings) {
            String[] rangeNumbers = a.split(" ");
            ranges.add(new Range(Long.parseLong(rangeNumbers[0]), Long.parseLong(rangeNumbers[1]), Long.parseLong(rangeNumbers[2])));
        }
    }

    public long processMap(long input){
        for(Range a : ranges){
            long modifier = a.processRange(input);
            if(modifier != 0){
                return input + modifier;
            }
        }
        return input;
    }
}
