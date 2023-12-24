import java.util.ArrayList;
import java.util.List;

public class HotSprings {
    List<Integer> pattern = new ArrayList<>();
    String springs;

    public HotSprings(String line) {
        String[] lineParts = line.split(" ");
        String[] patternParts = lineParts[1].split(",");

        springs = lineParts[0];
        for(String part : patternParts){
            if(Character.isDigit(part.charAt(0))){
                pattern.add(Integer.parseInt(part));
            }
        }
    }

    private boolean checkPattern(String springString){
        List<Integer> groups = new ArrayList<>();
        int springNum = 0;
        for(int i = 0; i < springString.length(); i++){
            if(springString.charAt(i) == '#'){
                springNum++;
            } else{
                if(springNum > 0){
                    groups.add(springNum);
                }
                springNum = 0;
            }
        }
        if(springNum > 0){
            groups.add(springNum);
        }

        if(pattern.size() != groups.size()){
            return false;
        }

        for(int i = 0; i < pattern.size(); i++){
            if(groups.get(i) != pattern.get(i)){
                return false;
            }
        }
        return true;
    }

    public int checkPatterns(){
        int matchingPatterns = 0;
        int unknowns = 0;
        for(int i = 0; i < springs.length(); i++){
            if(springs.charAt(i) == '?'){
                unknowns++;
            }
        }

        int possPatterns = (int) Math.pow(2, unknowns);
        for (int i=0; i<possPatterns; i++){
            String test = fillWithZeroes(Integer.toBinaryString(i), unknowns);
            StringBuilder builder = getTestString(test);

            if(checkPattern(builder.toString())){
                matchingPatterns++;
            }
        }

        return matchingPatterns;
    }

    private StringBuilder getTestString(String test) {
        int springIndex = 0;
        StringBuilder builder = new StringBuilder();
        for(int j=0; j<springs.length(); j++){
            if(springs.charAt(j) == '?'){
                char nextChar = '#';
                if(test.charAt(springIndex) == '0'){
                    nextChar = '.';
                }
                builder.append(nextChar);
                springIndex++;
            } else{
                builder.append(springs.charAt(j));
            }
        }
        return builder;
    }

    private String fillWithZeroes(String binaryString, int fillTo){
        int numZeroes =  fillTo - binaryString.length();
        StringBuilder newString = new StringBuilder();
        for(int i = 0; i < numZeroes; i++){
            newString.append('0');
        }
        newString.append(binaryString);
        return newString.toString();
    }
}
