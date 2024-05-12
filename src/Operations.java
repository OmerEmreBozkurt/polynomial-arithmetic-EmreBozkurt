/**
 * Class of method which decides operation, operation methods and other methods which is needed for operations.
 */
public class Operations {
    /**
     * This method calls the operation method we need
     * @param operator  Char that indicates which operation method should be returned.
     * @param firstPolynomial Linked list of first polynomial to make calculation.
     * @param secondPolynomial Linked list of second polynomial to make calculation.
     * @return It returns a Linked list which holds result of operation
     */
    public static LinkedList  doOperation(char operator, LinkedList firstPolynomial, LinkedList secondPolynomial) {
        if (operator == '+') {return addition(firstPolynomial, secondPolynomial);}
        else if (operator == '-') {return substraction(firstPolynomial, secondPolynomial);}
        else if (operator == '*') {return multiplication(firstPolynomial, secondPolynomial);}
        else {return null;}
    }

    /**
     * Calls search method from LinkedList class to find node which has given power values in given polynomial.
     * @param polynomial Linked-list of polynomial to be searched for matching node
     * @param powerOfX Power value for x to be searched.
     * @param powerOfY Power value for y to be searched.
     * @param powerOfZ Power value for z to be searched.
     * @return  returns an int which is coefficient value of node that matches with given power values. If there is no any matching node, it returns 0.
     */
    public static int findCoefficientOfMatchingNode(LinkedList polynomial, int powerOfX, int powerOfY, int powerOfZ){
        Node matchingNode = polynomial.search(powerOfX,powerOfY,powerOfZ); // searches for matching node
        if (matchingNode != null) { //check if any matching node exists
            return matchingNode.getCoefficient(); //returns coefficient of matching node
        } else {return 0;}  //if there is no any matching node, it returns 0.
    }

    /**
     * This method adds two polynomials and return result of addition operation.
     * @param firstPolynomial Linked-List that holds first polynomial.
     * @param secondPolynomial Linked-List that holds second polynomial.
     * @return returns result of addition.
     */
    public static LinkedList addition(LinkedList firstPolynomial, LinkedList secondPolynomial) {
        LinkedList result = new LinkedList();
        while (!firstPolynomial.isEmpty()){  //adds first polynomials coefficients and second polynomials matching nodes coefficient. After calculation deletes nodes which used in calculation
            Node firstTerm = firstPolynomial.head;
            int firstCoefficient = firstTerm.coefficient;
            int PoX = firstTerm.powerOfX;
            int PoY = firstTerm.powerOfY;
            int PoZ = firstTerm.powerOfZ;
            int secondCoefficient = findCoefficientOfMatchingNode(secondPolynomial,PoX,PoY,PoZ);
            if (secondCoefficient != 0) {
                result.insertLast(new Node(firstCoefficient + secondCoefficient, PoX, PoY, PoZ));
                secondPolynomial.deleteWithPower(PoX, PoY, PoZ);
                firstPolynomial.deleteFirst();
            } else {
                result.insertLast(firstTerm);
                firstPolynomial.deleteFirst();
            }
        }
        while (!secondPolynomial.isEmpty()){
            Node term = secondPolynomial.head;
            result.insertLast(new Node(term.coefficient, term.getPowerOfX(), term.powerOfY, term.powerOfZ));
            secondPolynomial.deleteFirst();
        }
        result.deleteZeroCoefficients();
        result.sortPolynomial();
        return result;
    }

    /**
     * This method subtracts two polynomials and return result of subtraction operation.
     * @param firstPolynomial Linked-List that holds first polynomial.
     * @param secondPolynomial Linked-List that holds second polynomial.
     * @return returns result of subtraction.
     */
    public static LinkedList substraction(LinkedList firstPolynomial, LinkedList secondPolynomial) {
        LinkedList result = new LinkedList();
        while (!firstPolynomial.isEmpty()){     //subtracts second polynomials matching nodes coefficient from first polynomials coefficients. After calculation deletes nodes which used in calculation
            Node firstTerm = firstPolynomial.head;
            int firstCoefficient = firstTerm.coefficient;
            int PoX = firstTerm.powerOfX;
            int PoY = firstTerm.powerOfY;
            int PoZ = firstTerm.powerOfZ;
            int secondCoefficient = findCoefficientOfMatchingNode(secondPolynomial,PoX,PoY,PoZ);
            if (secondCoefficient != 0) {
                result.insertLast(new Node(firstCoefficient - secondCoefficient, PoX, PoY, PoZ));
                secondPolynomial.deleteWithPower(PoX, PoY, PoZ);
                firstPolynomial.deleteFirst();
            } else {
                result.insertLast(firstTerm);
                firstPolynomial.deleteFirst();
            }
        }
        while (!secondPolynomial.isEmpty()){
            Node term = secondPolynomial.head;
            result.insertLast(new Node(-term.coefficient, term.getPowerOfX(), term.powerOfY, term.powerOfZ));
            secondPolynomial.deleteFirst();
        }
        result.deleteZeroCoefficients();
        result.sortPolynomial();
        return result;
    }

    /**
     * This method multiples two polynomials and return result of multiplication operation.
     * @param firstPolynomial Linked-List that holds first polynomial.
     * @param secondPolynomial Linked-List that holds second polynomial.
     * @return returns result of multiplication.
     */
    public static LinkedList multiplication(LinkedList firstPolynomial, LinkedList secondPolynomial) {
        int firstPolynomialNodeCount = firstPolynomial.numberOfElements();
        int secondPolynomialNodeCount = secondPolynomial.numberOfElements();
        LinkedList result = new LinkedList();
        for (int i = 0; i < firstPolynomialNodeCount; i++) { //multiplies every nodes coefficient in first polynomial with every nodes coefficient in second polynomial. Also adds power values of these nodes.
            Node firstNode = firstPolynomial.getNodeI(i);
            for (int j = 0; j < secondPolynomialNodeCount; j++) {
                Node secondNode = secondPolynomial.getNodeI(j);
                int resultCoefficient = firstNode.coefficient * secondNode.coefficient;
                int resultPoX = firstNode.powerOfX + secondNode.powerOfX;
                int resultPoY = firstNode.powerOfY + secondNode.powerOfY;
                int resultPoZ = firstNode.powerOfZ + secondNode.powerOfZ;
                Node existingNode = result.search(resultPoX,resultPoY,resultPoZ);  // check if result polynomial already has a node with same powers
                if (existingNode == null) {result.insertLast(new Node(resultCoefficient,resultPoX,resultPoY,resultPoZ));}
                else {existingNode.coefficient += resultCoefficient;}
            }
        }
        result.deleteZeroCoefficients();
        result.sortPolynomial();
        return result;
    }
}
