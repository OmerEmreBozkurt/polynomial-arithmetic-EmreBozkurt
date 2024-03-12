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
     *
     * @param PoX Power of x value to be searched
     * @param PoY Power of y value to be searched
     * @param PoZ Power of z value to be searched
     * @return Node that has the power value. If no node exists, returns null.
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

    /**
     * deletes node that has 0 as a coefficient
     */
    public void deleteZeroCoefficients(){
        boolean status = true;
        while (status) status = this.deleteWithCoefficient(0);
    }

    /**
     * deletes term that has provided coefficient value
     * @param coefficient coefficient value to be searched
     * @return boolean which indicates deletion operation is successful or not.
     */
    public boolean deleteWithCoefficient(int coefficient){
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
                return true;
            }
            previous = tmp;
            tmp = tmp.getNext();
        }
        return false;
    }

//  Delete method which use powers

    /**
     *  deletes term that has provided power values
     * @param PoX Power of x value to be searched
     * @param PoY Power of y value to be searched
     * @param PoZ Power of z value to be searched
     */
    public void deleteWithPower(int PoX, int PoY, int PoZ){
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

    // will be modified
    public void sortPolynomial(){
        for (int i = 0; i < this.numberOfElements(); i++) {
            Node temp = getNodeI(i);
            if (temp == tail){break;}
            Node tempNext = temp.next;
            if (tempNext != null && temp.getPowerOfX() < tempNext.getPowerOfX()) {
                swap(temp,tempNext);
            } else if (tempNext != null && temp.getPowerOfX() == tempNext.getPowerOfX()) {
                if (temp.getPowerOfY() < tempNext.getPowerOfY()) {
                    swap(temp,tempNext);
                } else if (temp.getPowerOfY() == tempNext.getPowerOfY()) {
                    if (temp.getPowerOfZ() < tempNext.getPowerOfZ()) {
                        swap(temp,tempNext);
                    }
                }
            }
        }
        if (!checkSorted()) sortPolynomial();
    }

    public boolean checkSorted(){
        if (head != null) {
            int maxX = this.head.powerOfX;
            for (int i = 0; i < this.numberOfElements(); i++) {
                Node temp = getNodeI(i);
                if (temp.getPowerOfX() > maxX) {return false;}
                if (temp.next != null) {
                    if (temp.getPowerOfX() == 0 && temp.getPowerOfY() < temp.next.powerOfY) {
                        return false;
                    }
                    if (temp.getPowerOfX() == 0 && temp.getPowerOfY() == 0 && temp.getPowerOfZ() < temp.next.powerOfZ) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void swap (Node node1, Node node2) {
        if (node1 != head) {
            getPrevious(node1).setNext(node2);
            node1.next = node2.next;
            node2.next = node1;
        } else {
            node1.next = node2.next;
            insertFirst(node2);
        }
    }

    public String toString(){
        StringBuilder result = new StringBuilder();
        if (head == null) return "0";
        Node tmp = head;
        while (tmp != null) {
            result.append(tmp).append("");
            tmp = tmp.getNext();
        }
        return result.toString();
    }
}