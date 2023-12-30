import java.util.ArrayList;
import java.util.List;

public class RockPlot {
    char[][] plot;

    public RockPlot(List<String> fileLines) {
        plot = new char[fileLines.get(0).length()][fileLines.size()];
        for(int y = 0; y < fileLines.size(); y++) {
            for(int x = 0; x < fileLines.get(0).length(); x++) {
                plot[x][y] = fileLines.get(y).charAt(x);
            }
        }
    }

    public RockPlot(char[][] plot) {
        this.plot = plot;
    }

    private void tiltNorth() {
        for(int x = 0; x < plot[0].length; x++) {
            for(int y = 1; y < plot.length; y++) {
                if(plot[x][y] == 'O') {
                    for(int subY = y - 1; subY >= 0; subY--) {
                        if(plot[x][subY] == '#' || plot[x][subY] == 'O') {
                            plot[x][y] = '.';
                            plot[x][subY + 1] = 'O';
                            break;
                        } else {
                             if(subY == 0 && plot[x][subY] == '.') {
                                 plot[x][y] = '.';
                                 plot[x][subY] = 'O';
                             }
                        }
                    }
                }
            }
        }
    }

    private void tiltSouth() {
        for(int x = 0; x < plot[0].length; x++) {
            for(int y = plot.length - 2; y >= 0; y--) {
                if(plot[x][y] == 'O') {
                    for(int subY = y + 1; subY < plot.length; subY++) {
                        if(plot[x][subY] == '#' || plot[x][subY] == 'O') {
                            plot[x][y] = '.';
                            plot[x][subY - 1] = 'O';
                            break;
                        } else {
                            if(subY == plot.length - 1 && plot[x][subY] == '.') {
                                plot[x][y] = '.';
                                plot[x][subY] = 'O';
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    private void tiltWest() {
        for(int y = 0; y < plot.length; y++) {
            for(int x = 0; x < plot[0].length; x++) {
                if(plot[x][y] == 'O') {
                    for(int subX = x - 1; subX >= 0; subX--) {
                        if(plot[subX][y] == '#' || plot[subX][y] == 'O') {
                            plot[x][y] = '.';
                            plot[subX + 1][y] = 'O';
                            break;
                        } else {
                            if(subX == 0 && plot[subX][y] == '.') {
                                plot[x][y] = '.';
                                plot[subX][y] = 'O';
                            }
                        }
                    }
                }
            }
        }
    }

    private void tiltEast() {
        for(int y = 0; y < plot.length; y++) {
            for(int x = plot[0].length - 1; x >=0; x--) {
                if(plot[x][y] == 'O') {
                    for(int subX = x + 1; subX <= plot[0].length -1; subX++) {
                        if(plot[subX][y] == '#' || plot[subX][y] == 'O') {
                            plot[x][y] = '.';
                            plot[subX - 1][y] = 'O';
                            break;
                        } else {
                            if(subX == plot[0].length - 1 && plot[subX][y] == '.') {
                                plot[x][y] = '.';
                                plot[subX][y] = 'O';
                            }
                        }
                    }
                }
            }
        }
    }

    public void tiltCycle() {
        tiltNorth();
        tiltWest();
        tiltSouth();
        tiltEast();
    }

    public int getRockWeight() {
        int rockWeight = 0;
        for(int y = plot.length - 1; y >= 0; y--){
            for(int x = 0; x < plot[0].length; x++) {
                if(plot[x][y] == 'O') {
                    rockWeight += plot.length - y;
                }
            }
        }
        return rockWeight;
    }

    public char[][] getPlot() {
        return plot;
    }

    public boolean equals (Object obj) {
        if(!(obj instanceof RockPlot)){
            return false;
        }

        for(int y = 0; y < this.plot.length; y++){
            for(int x = 0; x < this.plot[0].length; x++) {
                if(this.plot[x][y] != ((RockPlot) obj).getPlot()[x][y]){
                    return false;
                }
            }
        }

        return true;
    }

    public RockPlot copy(){
        char[][] newPlot = new char[plot.length][plot[0].length];

        for(int y = 0; y < this.plot.length; y++){
            for(int x = 0; x < this.plot[0].length; x++) {
                newPlot[x][y] = plot[x][y];
            }
        }

        return new RockPlot(newPlot);
    }
}
