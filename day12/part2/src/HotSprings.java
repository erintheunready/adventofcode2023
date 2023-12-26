import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HotSprings {
    List<Integer> pattern = new ArrayList<>();
    String springs;
    static HashMap<String, Long> solutionMap = new HashMap<>();

    public HotSprings(String line) {
        String[] lineParts = line.split(" ");
        String springPart = lineParts[0];
        String patternPart = lineParts[1];



        String fullPattern = String.join(",", patternPart, patternPart, patternPart, patternPart, patternPart);
        springs = String.join("?", List.of(springPart, springPart, springPart, springPart, springPart));

        String[] patternParts = fullPattern.split(",");

        for(String part : patternParts){
            if(Character.isDigit(part.charAt(0))){
                pattern.add(Integer.parseInt(part));
            }
        }

        System.out.println(pattern.toString() + " " + springs);
    }

    public String getSprings() {
        return springs;
    }

    public List<Integer> getPattern(){
        return pattern;
    }

    public static long calculateMatchingPatterns(String springs, List<Integer> pattern){
        String hash = generateHash(springs, pattern);
        if(solutionMap.containsKey(hash)){
            return solutionMap.get(hash);
        }

        if(springs.isEmpty()){
            if(pattern.isEmpty()){
                solutionMap.put(hash, 1L);
                return 1;
            }
            solutionMap.put(hash, 0L);
            return 0;
        }

        if(pattern.isEmpty()){
            if(!springs.contains("#")){
                solutionMap.put(hash, 1L);
                return 1;
            }

            solutionMap.put(hash, 0L);
            return 0;
        }

        int thisGroup = pattern.get(0);

        if(thisGroup > springs.length()){
            solutionMap.put(hash, 0L);
            return 0;
        }

        if(thisGroup == springs.length()){
            // Invalid patterns
            if(pattern.size() > 1){
                solutionMap.put(hash, 0L);
                return 0;
            }

            if(springs.contains(".")) {
                solutionMap.put(hash, 0L);
                return 0;
            }

            // Valid pattern
            solutionMap.put(hash, 1L);
            return 1;
        }

        long matchingPatterns = 0;

        // Move on to next pattern if this is not a valid part
        if(springs.charAt(0) == '.') {
            matchingPatterns += calculateMatchingPatterns(springs.substring(1), pattern);
        } else {
            String thisString = springs.substring(0, thisGroup);

            // If this group works, proceed to next part of string
            if(!thisString.contains(".") && (springs.charAt(thisGroup) == '.' || springs.charAt(thisGroup) == '?')){
                matchingPatterns += calculateMatchingPatterns(springs.substring(thisGroup + 1), pattern.subList(1, pattern.size()));
            }

            // traverse path where question mark would be working spring
            if(thisString.charAt(0) == '?'){
                matchingPatterns += calculateMatchingPatterns(springs.substring(1), pattern);
            }
        }

        solutionMap.put(hash, matchingPatterns);
        return matchingPatterns;
    }

    private static String generateHash(String springs, List<Integer> pattern) {
        return springs + "|" + pattern.toString();
    }
}
