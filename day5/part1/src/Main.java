import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception{
        List<String> fileLines = InputParser.getLinesFromLocalFile("input.txt");

        String seedString = fileLines.remove(0);
        fileLines.remove(0);
        String[] seedStrings = seedString.split(" ");
        List<Long> seeds = new ArrayList<>();

        for(String a : seedStrings){
            if(!a.isEmpty() && Character.isDigit(a.charAt(0))){
                seeds.add(Long.parseLong(a));
            }
        }
        long firstSeed = seeds.remove(0);

        List<MappingRanges> maps = generateMaps(fileLines);
        long minLocation = processSeed(firstSeed, maps);
        for(long seed : seeds){
            long location = processSeed(seed,maps);
            if(location < minLocation){
                minLocation = location;
            }
        }

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
}
