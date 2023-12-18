import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception{
        List<String> fileLines = InputParser.getLinesFromLocalFile("input.txt");
        List<List<Sequence>> seqLists = new ArrayList<>();

        for(String line : fileLines){
            String[] lineParts = line.split("\\s+");
            List<Integer> currNums = new ArrayList<>();
            List<Sequence> seqs = new ArrayList<>();
            for (String linePart : lineParts) {
                currNums.add(Integer.parseInt(linePart));
            }
            Sequence seq = new Sequence(currNums);
            seqs.add(seq);
            while(seq.isDiff()){
                seq = seq.getSubSequence();
                seqs.add(seq);
            }
            seqLists.add(seqs);
        }

        List<Integer> seqSums = new ArrayList<>();
        for(List<Sequence> seqSeries : seqLists){
            int currNum = 0;
            for(int i = seqSeries.size()- 2; i >= 0; i--){
                currNum = seqSeries.get(i).getInitial() - currNum;
            }
            seqSums.add(currNum);
        }

        int sum = seqSums.stream().mapToInt(e -> e).sum();

        System.out.println("Sum: " + sum);
    }
}
