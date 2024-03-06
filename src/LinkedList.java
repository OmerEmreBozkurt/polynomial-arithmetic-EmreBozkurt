public class LinkedList {

    protected Node head;
    protected Node tail;

    public LinkedList() {
        head = null;
        tail = null;
    }

    public boolean isEmpty(){
        return head == null;
    }

    public Node getHead(){
        return head;
    }

    public void insertFirst(Node newNode) {
        if (isEmpty()) {
            tail = newNode;
        }
        newNode.setNext(head);
        head = newNode;
    }

    public void insertLast(Node newNode) {
        if (isEmpty()) {
            head = newNode;
        } else {
            tail.setNext(newNode);
        }
        tail = newNode;
    }

    public void insertMiddle(Node newNode, Node previous) {
        newNode.setNext(previous.getNext());
        previous.setNext(newNode);
    }

    // Search method modified
    /**
     * @param PoX, PoY, PoZ The value to be searched.
     * @return The node that has the data value. If no node exists, returns null.
     */
    public Node search(int PoX, int PoY, int PoZ) {
        Node tmp = head;
        while (tmp != null) {
            if (tmp.getPowerOfX() == PoX && tmp.getPowerOfY() == PoY && tmp.getPowerOfZ() == PoZ) {
                return tmp;
            }
            tmp = tmp.getNext();
        }
        return null;
    }

    public Node getNodeI(int i) {
        Node tmp = head;
        int index = 0;
        while (tmp != null) {
            if (index == i){
                return tmp;
            }
            index++;
            tmp = tmp.getNext();
        }
        return null;
    }

    public int numberOfElements(){
        Node tmp = head;
        int count = 0;
        while (tmp != null) {
            count++;
            tmp = tmp.getNext();
        }
        return count;
    }

    public void deleteFirst(){
        head = head.getNext();
        if (isEmpty()){
            tail = null;
        }
    }

    public Node getPrevious(Node node){
        Node tmp = head;
        Node previous = null;
        while (tmp != node) {
            previous = tmp;
            tmp = tmp.getNext();
        }
        return previous;
    }

    //deletes terms which has 0 as coefficient
    public void deleteZeroCoefficients(){
        this.deleteFromCoefficient(0);
    }


    //  Delete method for known Coefficient
    public void deleteFromCoefficient(int coefficient) {
        Node tmp = head;
        Node previous = null;
        while (tmp != null) {
            if (tmp.getCoefficient() == coefficient){
                if (previous != null){
                    previous.setNext(tmp.next);
                    if (tmp.next == null){
                        tail = previous;
                    }
                } else {
                    head = tmp.next;
                    if (head == null){
                        tail = null;
                    }
                }
                break;
            }
            previous = tmp;
            tmp = tmp.getNext();
        }
    }

    //  Delete method for known powers
    public void deleteFromPowers(int PoX, int PoY, int PoZ){
        Node tmp = head;
        Node previous = null;
        while (tmp != null) {
            if (tmp.getPowerOfX() == PoX && tmp.getPowerOfY() == PoY && tmp.getPowerOfZ() == PoZ){
                if (previous != null){
                    previous.setNext(tmp.next);
                    if (tmp.next == null){
                        tail = previous;
                    }
                } else {
                    head = tmp.next;
                    if (head == null){
                        tail = null;
                    }
                }
                break;
            }
            previous = tmp;
            tmp = tmp.getNext();
        }
    }

    public void deleteLast(){
        tail = getPrevious(tail);
        if (tail != null){
            tail.setNext(null);
        } else {
            head = null;
        }
    }

    public void deleteMiddle(Node node){
        Node previous;
        previous = getPrevious(node);
        previous.setNext(node.getNext());
    }

    public String toString(){
        StringBuilder result = new StringBuilder();
        Node tmp = head;
        while (tmp != null) {
            result.append(tmp).append("");
            tmp = tmp.getNext();
        }
        return result.toString();
    }
}