import java.util.ArrayList;
import java.util.List;

public class Pattern {
    private final List<String> rows = new ArrayList<>();
    private final List<String> columns = new ArrayList<>();

    public Pattern(List<String> patternLines) {
        this.rows.addAll(patternLines);
        for(int i = 0; i < patternLines.get(0).length(); i++){
            StringBuilder colString = new StringBuilder();
            for(String line : patternLines){
                colString.append(line.charAt(i));
            }
            this.columns.add(colString.toString());
        }
    }

    public int findReflectionQuantity() {
        int rowRef = findRowReflection();
        if(rowRef == 0){
            return findColumnReflection();
        } else{
            return rowRef * 100;
        }
    }

    private int findRowReflection() {
        for(int i = 1; i < rows.size(); i ++){
            if(checkReflection(rows, i)){
                return i;
            }
        }
        return 0;
    }

    private int findColumnReflection() {
        for(int i = 1; i < columns.size(); i ++){
            if(checkReflection(columns, i)){
                return i;
            }
        }
        return 0;
    }

    private boolean checkReflection(List<String> lines, int i) {
        int checkSpan = 0;
        while(i - checkSpan > 0 && i + checkSpan < lines.size()){
            if(!lines.get(i - checkSpan - 1).equals(lines.get(i + checkSpan))){
                return false;
            }
            checkSpan++;
        }
        return true;
    }
}
