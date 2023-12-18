import java.util.ArrayList;
import java.util.List;

public class Sequence {
    private List<Integer> seq = new ArrayList<>();
    private boolean hasDiff = false;

    public Sequence(List<Integer> seq){
        this.seq = seq;
        hasDiff = seq.stream().anyMatch(e -> e != 0);
    }

    public Sequence getSubSequence() {
        List<Integer> subSeq = new ArrayList<>();
        for(int i = 0; i < seq.size() - 1; i++){
            int diff = seq.get(i+1) - seq.get(i);
            subSeq.add(diff);
        }
        return new Sequence(subSeq);
    }

    public int getInitial() {
        return seq.get(0);
    }

    public boolean isDiff() {
        return hasDiff;
    }

    public List<Integer> getSeq() {
        return seq;
    }
}
