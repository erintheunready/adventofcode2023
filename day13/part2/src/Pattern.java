import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pattern {
    private final ArrayList<String> rows = new ArrayList<>();
    private final ArrayList<String> columns = new ArrayList<>();

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
        int colRef = findColumnReflection();
        for(int y = 0; y < rows.size(); y++) {
            for(int x = 0; x < columns.size(); x++){
                int testRes = testSmudge(new int[]{x,y}, rowRef, colRef);
                if(testRes > 0) {
                    return testRes;
                }
            }
        }
        return 0;
    }

    private int testSmudge(int[] coord, int currRow, int currCol) {
        List<String> testRows = new ArrayList<>(rows);
        List<String> testCols = new ArrayList<>(columns);

        String origString = testRows.get(coord[1]);
        char flipChar = origString.charAt(coord[0]);

        if(flipChar == '#') {
            flipChar = '.';
        } else {
            flipChar = '#';
        }
        String newString = origString.substring(0, coord[0]) + flipChar + origString.substring(coord[0] + 1);
        testRows.set(coord[1], newString);

        int rowRef = findRowReflection(testRows, currRow);
        if(rowRef > 0) {
            return rowRef * 100;
        }


        origString = testCols.get(coord[0]);
        flipChar = origString.charAt(coord[1]);
        if(flipChar == '#') {
            flipChar = '.';
        } else {
            flipChar = '#';
        }
        newString = origString.substring(0, coord[1]) + flipChar + origString.substring(coord[1] + 1);
        testCols.set(coord[0], newString);

        int colRef = findColumnReflection(testCols, currCol);
        return Math.max(colRef, 0);
    }

    private int findRowReflection(List<String> rows, int currRef) {
        for(int i = 1; i < rows.size(); i ++){
            if(i != currRef && checkReflection(rows, i)){
                return i;
            }
        }
        return 0;
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

    private int findColumnReflection(List<String> columns, int currRef) {
        for(int i = 1; i < columns.size(); i ++){
            if(i != currRef && checkReflection(columns, i)){
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
