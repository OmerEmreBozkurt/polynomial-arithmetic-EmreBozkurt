/*Modified Node class in order to hold coefficient,exponent as a int and variableName as a String*/
public class Node {
    protected int coefficient, powerOfX, powerOfY, powerOfZ;
    protected Node next;

    public Node(int coefficient, int powerOfX, int powerOfY, int powerOfZ){
        this.coefficient = coefficient;
        this.powerOfX = powerOfX;
        this.powerOfY = powerOfY;
        this.powerOfZ = powerOfZ;
        this.next = null;
    }

    public void setNext(Node next){
        this.next = next;
    }

    public Node getNext(){
        return next;
    }

    public int getCoefficient() {
        return coefficient;
    }

    public int getPowerOfX() {
        return powerOfX;
    }

    public int getPowerOfY() {
        return powerOfY;
    }

    public int getPowerOfZ() {
        return powerOfZ;
    }

    public String toString(){
        StringBuilder termStr = new StringBuilder();
        return "" + coefficient + "x" + powerOfX + "y" + powerOfY + "z" + powerOfZ ;
    }

}
