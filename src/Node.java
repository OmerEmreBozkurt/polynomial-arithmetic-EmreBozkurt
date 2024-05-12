/**
 * Modified Node class in order to hold coefficient,exponent as int
 */
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

    /**
     * Check possible conditions of values of coefficient and powers.
     * Builds a String for node.
     * @return returns builded string.
     */
    public String toString(){
        StringBuilder term = new StringBuilder();

        if (coefficient != 1) {
            if (coefficient == -1) {term.append("-");}
            else {term.append(coefficient);}
        }

        if (powerOfX != 0){
            term.append("x");
            if (powerOfX != 1) {
                term.append(powerOfX);
            }
        }
        if (powerOfY != 0){
            term.append("y");
            if (powerOfY != 1) {
                term.append(powerOfY);
            }
        }
        if (powerOfZ != 0){
            term.append("z");
            if (powerOfZ != 1) {
                term.append(powerOfZ);
            }
        }
        if (next != null && next.coefficient > 0) {term.append("+");}

        return term.toString() ;
    }

}
