import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        String input = InputParser.getLinesFromLocalFile("input.txt").get(0);

        List<String> instructions = Arrays.asList(input.split(","));

        long sum = instructions.stream().mapToLong(e -> {
            int hash = 0;
            for(int i = 0; i < e.length(); i++){
                hash += (int) e.charAt(i);
                hash *= 17;
                hash %= 256;
            }
            return hash;
        }).sum();

        System.out.println(sum);
    }
}
