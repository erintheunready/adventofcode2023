import java.util.List;

public class Node {
    private String left;
    private String right;

    public Node(String left, String right){
        this.left = left;
        this.right = right;
    }

    public String getLeft(){
        return left;
    }

    public String getRight() {
        return right;
    }
}
