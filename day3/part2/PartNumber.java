class PartNumber {

  private int number;
  private int length;
  private int x;
  private int y;

  public PartNumber(int number, int x, int y) {
    this.number = number;
    this.length = String.valueOf(number).length();
    this.y = y;
    this.x = x - length;
  }

  public boolean isAdjacentTo(int compareX, int compareY){
    return (compareX >= x - 1 && compareX <= x+length && compareY >= y - 1 && compareY <= y+1);
  }

  public int getNumber(){
    return number;
  }
}
