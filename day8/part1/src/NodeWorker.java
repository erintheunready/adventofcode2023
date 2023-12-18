import java.util.Map;

class NodeWorker {
    private String currNodeName;
    private Node currNode;
    private boolean isFinished = false;
    private int numSteps = 0;

    public NodeWorker(String nodeName, Node node){
        this.currNodeName = nodeName;
        this.currNode = node;
    }

    public boolean advanceToNextNode(Map<String, Node> nodeMap, boolean isLeft){
        numSteps++;
        if(isLeft){
            currNodeName = currNode.getLeft();
        } else{
            currNodeName = currNode.getRight();
        }
        currNode = nodeMap.get(currNodeName);

        if(currNodeName.charAt(2) == 'Z'){
            isFinished = true;
            return true;
        }
        return false;
    }

    public int getNumSteps(){
        return numSteps;
    }

    public boolean isFinished(){
        return isFinished;
    }

}