import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception{
        List<String> fileLines = InputParser.getLinesFromLocalFile("input.txt");

        String seedString = fileLines.remove(0);
        fileLines.remove(0);
        String[] seedStrings = seedString.split(" ");
        List<Long> seedPairs = new ArrayList<>();

        for(String a : seedStrings){
            if(!a.isEmpty() && Character.isDigit(a.charAt(0))){
                seedPairs.add(Long.parseLong(a));
            }
        }

        List<MappingRanges> maps = generateMaps(fileLines);
        long minLocation = findMinimumInSeedList(seedPairs, maps);

        System.out.println("Minimum location: " + minLocation);
    }

    public static List<MappingRanges> generateMaps(List<String> fileLines){
        boolean createNewMap = false;
        List<MappingRanges> maps = new ArrayList<>();
        List<String> currLines = new ArrayList<>();

        for(String line : fileLines){
            if (!line.isEmpty() && Character.isDigit(line.charAt(0))) {
                currLines.add(line);
                createNewMap = true;
            }
            if(createNewMap && line.isEmpty() && !currLines.isEmpty()){
                maps.add(new MappingRanges(currLines));
                currLines.clear();
                createNewMap = false;
            }
        }
        if(createNewMap && !currLines.isEmpty()){
            maps.add(new MappingRanges(currLines));
            currLines.clear();
            createNewMap = false;
        }
        System.out.println(maps.size());

        return maps;
    }

    public static long processSeed(long seed, List<MappingRanges> maps){
        long location = seed;
        for(MappingRanges map : maps){
            location = map.processMap(location);
        }
        return location;
    }

    public static long findMinimumInSeedList(List<Long> seedPairList, List<MappingRanges> maps){
        long firstSeed = seedPairList.get(0);
        long minLocation = processSeed(firstSeed, maps);

        long seedStart = 0;
        for(int i = 0; i < seedPairList.size(); i++){
            if(i % 2 == 0){
                seedStart = seedPairList.get(i);
            } else{
                long seedEnd = seedPairList.get(i);
                for(long j = 0; j < seedEnd; j++){
                    long location = processSeed(seedStart+j,maps);
                    if(location < minLocation){
                        System.out.println("found new minimum: " + location);
                        minLocation = location;
                    }
                }
            }
        }

        return minLocation;
    }
}
