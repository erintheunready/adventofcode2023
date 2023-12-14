import java.util.ArrayList;
import java.util.List;

public class Gear {
    private int x;
    private int y;

    public Gear(int x, int y){
        this.x = x;
        this.y = y;
    }

    private List<Integer> getAdjacentNumbers(List<PartNumber> numberList){
        List<Integer> adjacentNumbers = new ArrayList<>();
        for(PartNumber n : numberList) {
            if(n.isAdjacentTo(x, y)){
                adjacentNumbers.add(n.getNumber());
            }
        }
        
        return adjacentNumbers;
    }

    public int getValueIfValid(List<PartNumber> numberList){
        List<Integer> adjacentNumbers = getAdjacentNumbers(numberList);
        if(adjacentNumbers.size() == 2){
            return adjacentNumbers.get(0) * adjacentNumbers.get(1);
        }

        return 0;
    }
}
