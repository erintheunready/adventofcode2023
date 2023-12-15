package day4.part1;

import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        List<String> fileLines = InputParser.getLinesFromLocalFile("input.txt");
        List<Game> allGames = fileLines.stream().map(Game::new).toList();

        int sum = allGames.stream().mapToInt(Game::computeWinSum).sum();

        System.out.println("Sum: " + sum);
    }
}