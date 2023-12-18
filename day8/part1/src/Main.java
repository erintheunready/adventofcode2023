import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        List<String> fileLines = InputParser.getLinesFromLocalFile("input.txt");

        String instruction = fileLines.remove(0).trim();
        String[] instructionSet = instruction.split("");
        System.out.println(Arrays.toString(instructionSet));

        fileLines.remove(0);

        Map<String, Node> nodeMap = new HashMap<>();
        List<NodeWorker> workerNodeList = new ArrayList<>();

        for(String line : fileLines){
            String[] lineParts = line.replaceAll("[^A-Za-z0-9 ]", "").split("\\s+");
            Node pushNode = new Node(lineParts[1], lineParts[2]);
            nodeMap.put(lineParts[0], pushNode);
            if(lineParts[0].charAt(2) == 'A'){
                workerNodeList.add(new NodeWorker(lineParts[0], pushNode));
            }
        }

        int dirIndex = 0;
        int steps = 0;
        List<NodeWorker> arrived = new ArrayList<>();

        while(arrived.size() < workerNodeList.size() && steps < 1000000){
            boolean isLeft = Objects.equals(instructionSet[dirIndex], "L");
            for(NodeWorker nodeWorker : workerNodeList){
                if(!nodeWorker.isFinished() && nodeWorker.advanceToNextNode(nodeMap, isLeft)){
                    arrived.add(nodeWorker);
                }
            }
            dirIndex ++;
            if(dirIndex >= instructionSet.length){
                dirIndex = 0;
            }
            steps++;
        }

        long[] stepCounts = new long[workerNodeList.size()];
        for(int i = 0; i < workerNodeList.size(); i++){
            stepCounts[i] = workerNodeList.get(i).getNumSteps();
        }

        System.out.println(lcm(stepCounts));
    }

    private static long lcm(long a, long b)
    {
        return a * (b / gcd(a, b));
    }

    private static long gcd(long a, long b)
    {
        while (b > 0)
        {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private static long gcd(long[] input)
    {
        long result = input[0];
        for(int i = 1; i < input.length; i++) result = gcd(result, input[i]);
        return result;
    }

    private static long lcm(long[] input)
    {
        long result = input[0];
        for(int i = 1; i < input.length; i++) result = lcm(result, input[i]);
        return result;
    }
}
