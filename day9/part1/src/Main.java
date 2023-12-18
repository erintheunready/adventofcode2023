import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

        int sum = seqLists.stream().mapToInt(e -> e.stream().mapToInt(Sequence::getFinal).sum()).sum();
        System.out.println(sum);
    }
}
