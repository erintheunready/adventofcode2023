import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception{
        List<String> fileLines = InputParser.getLinesFromLocalFile("input.txt");

        RockPlot plot = new RockPlot(fileLines);
        int loopLength = 0;
        List<RockPlot> plotSteps = new ArrayList<>();

        plotSteps.add(plot.copy());
        int repeatIndex = 0;
        for(long i = 0; i < 10000; i++){
            plot.tiltCycle();
            repeatIndex = plotSteps.indexOf(plot);
            if(repeatIndex > -1){
                loopLength = plotSteps.size() - repeatIndex;
                break;
            }
            plotSteps.add(plot.copy());
        }

        long loopPosition = (1000000000L - repeatIndex) % loopLength;
        for(long i = 0; i < loopPosition; i++){
            plot.tiltCycle();
        }

        print(plot.getPlot());

        System.out.println(plot.getRockWeight());
    }

    public static void print(char[][] plot) {
        for(int y = 0; y < plot.length; y++) {
            for(int x = 0; x < plot[y].length; x++) {
                System.out.print(plot[x][y]);
            }
            System.out.println();
        }
    }
}
