import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main (String[] args) throws Exception{
        List<String> fileLines = InputParser.getLinesFromLocalFile("input.txt");
        List<Long> times = getLineNums(fileLines.get(0));
        List<Long> distances = getLineNums(fileLines.get(1));
        List<Long[]> races = new ArrayList<>();
        List<Long> possTimes = new ArrayList<>();
        for(int i = 0; i < times.size(); i++){
            races.add(new Long[] {times.get(i), distances.get(i)});
        }
        for(Long[] race : races){
            possTimes.add(getPossTimeCount(race[0], race[1]));
        }

        System.out.println("Factor: " + possTimes.stream().reduce(1L, (a, b) -> a * b));
    }

    private static List<Long> getLineNums(String line) {
        List<Long> lineNums = new ArrayList<>();
        String[] splitLine = line.trim().split(" ");
        for(String item : splitLine){
            if(!item.isEmpty() && Character.isDigit(item.charAt(0))){
                lineNums.add(Long.parseLong(item));
            }
        }
        return lineNums;
    }

    private static long getPossTimeCount(long time, long distance){
        double[] answers = quadraticEquation(1, (-1 * time), distance);
        double lowerBoundFloat;
        double upperBoundFloat;
        long lowerBound;
        long upperBound;
        if(answers[0] < answers[1]){
            lowerBoundFloat = answers[0];
            upperBoundFloat = answers[1];
        } else{
            lowerBoundFloat = answers[1];
            upperBoundFloat = answers[0];
        }

        lowerBound = Math.round(Math.ceil(lowerBoundFloat));
        upperBound = Math.round(Math.floor(upperBoundFloat));
        long possNumCount = upperBound - lowerBound + 1;

        // precision check
        System.out.println(Arrays.toString(answers));
        if(!(lowerBound > lowerBoundFloat)) --possNumCount;
        if(!(upperBound < upperBoundFloat)) --possNumCount;
        System.out.println(possNumCount);

        return possNumCount;
    }

    private static double[] quadraticEquation(long a, long b, long c) {
        double sqrtPortion = Math.sqrt(Math.pow(b, 2) - (4 * a * c));
        return new double[] {((-b + sqrtPortion) / (2 * a)),  ((-b - sqrtPortion) / (2* a))};
    }
}
