public class Operations {
    /**
     * This method calls the operation method we need
     * @param operator  Char that indicates which operation method should returned
     * @param firstPolynomial
     * @param secondPolynomial
     * @return
     */
    public static LinkedList  doOperation(char operator, LinkedList firstPolynomial, LinkedList secondPolynomial) {
        if (operator == '+') {return addition(firstPolynomial, secondPolynomial);}
        else if (operator == '-') {return substraction(firstPolynomial, secondPolynomial);}
        else if (operator == '*') {return multiplication(firstPolynomial, secondPolynomial);}
        else {return null;}
    }

    public static int findCoefficientOfMatchingNode(LinkedList polynomial, int powerOfX, int powerOfY, int powerOfZ){
        Node matchingNode = polynomial.search(powerOfX,powerOfY,powerOfZ);
        if (matchingNode != null) {
            return matchingNode.getCoefficient();
        } else {return 0;}
    }

    public static LinkedList addition(LinkedList firstPolynomial, LinkedList secondPolynomial) {
        LinkedList result = new LinkedList();
        while (!firstPolynomial.isEmpty()){
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

    public static LinkedList substraction(LinkedList firstPolynomial, LinkedList secondPolynomial) {
        LinkedList result = new LinkedList();
        while (!firstPolynomial.isEmpty()){
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

    public static LinkedList multiplication(LinkedList firstPolynomial, LinkedList secondPolynomial) {
        int firstPolynomialNodeCount = firstPolynomial.numberOfElements();
        int secondPolynomialNodeCount = secondPolynomial.numberOfElements();
        LinkedList result = new LinkedList();
        for (int i = 0; i < firstPolynomialNodeCount; i++) {
            Node firstNode = firstPolynomial.getNodeI(i);
            for (int j = 0; j < secondPolynomialNodeCount; j++) {
                Node secondNode = secondPolynomial.getNodeI(j);
                int resultCoefficient = firstNode.coefficient * secondNode.coefficient;
                int resultPoX = firstNode.powerOfX + secondNode.powerOfX;
                int resultPoY = firstNode.powerOfY + secondNode.powerOfY;
                int resultPoZ = firstNode.powerOfZ + secondNode.powerOfZ;
                result.insertLast(new Node(resultCoefficient,resultPoX,resultPoY,resultPoZ));
            }
        }
        result.deleteZeroCoefficients();
        result.sortPolynomial();
        return result;
    }
}
