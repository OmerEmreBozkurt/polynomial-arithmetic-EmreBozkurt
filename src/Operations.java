public class Operations {
    /*This method calls the operation method we need*/
    public static LinkedList  doOperation(char operator, LinkedList[] polynomials) {
        if (operator == '+') {return addition(polynomials[0], polynomials[1]);}
        else if (operator == '-') {return subtruction(polynomials[0], polynomials[1]);}
        else if (operator == '*') {return null;}
        else {return null;}
    }

    public static int findCoefficientOfMatchingNode(LinkedList polynomial, int powerOfX, int powerOfY, int powerOfZ){
        Node matchingNode = polynomial.search(powerOfX,powerOfY,powerOfZ);
        if (matchingNode != null) {
            return matchingNode.getCoefficient();
        } else {return 0;}
    }

    public static LinkedList addition(LinkedList polynomial1, LinkedList polynomial2) {
        LinkedList firstPolynomial = polynomial1;
        LinkedList secondPolynomial = polynomial2;
        LinkedList result = new LinkedList();
        while (!firstPolynomial.isEmpty()){
            if (!secondPolynomial.isEmpty()) {
                int firstCoefficient = firstPolynomial.head.getCoefficient();
                int firstPOX = firstPolynomial.head.powerOfX;
                int firstPOY = firstPolynomial.head.powerOfY;
                int firstPOZ = firstPolynomial.head.powerOfZ;
                int coefficientOfMatchingNode = findCoefficientOfMatchingNode(secondPolynomial, firstPOX, firstPOY, firstPOZ);
                result.insertLast(new Node(firstCoefficient + coefficientOfMatchingNode, firstPOX, firstPOY, firstPOZ));
                firstPolynomial.deleteFirst();
                if (coefficientOfMatchingNode != 0) {secondPolynomial.deleteFirst();}
            } else {
                result.insertLast(new Node(firstPolynomial.head.coefficient, firstPolynomial.head.powerOfX, firstPolynomial.head.powerOfY, firstPolynomial.head.powerOfZ));
                firstPolynomial.deleteFirst();
            }
        }
        if (!secondPolynomial.isEmpty()) {
            while (!secondPolynomial.isEmpty()) {
                result.insertLast(new Node(secondPolynomial.head.coefficient, secondPolynomial.head.powerOfX, secondPolynomial.head.powerOfY, secondPolynomial.head.powerOfZ));
                secondPolynomial.deleteFirst();
            }
        }
        return result;
    }

    public static LinkedList subtruction(LinkedList polynomial1, LinkedList polynomial2) {
        LinkedList firstPolynomial = polynomial1;
        LinkedList secondPolynomial = polynomial2;
        LinkedList result = new LinkedList();
        while (!firstPolynomial.isEmpty()){
            if (!secondPolynomial.isEmpty()) {
                int firstCoefficient = firstPolynomial.head.getCoefficient();
                int firstPOX = firstPolynomial.head.powerOfX;
                int firstPOY = firstPolynomial.head.powerOfY;
                int firstPOZ = firstPolynomial.head.powerOfZ;
                int coefficientOfMatchingNode = findCoefficientOfMatchingNode(secondPolynomial, firstPOX, firstPOY, firstPOZ);
                result.insertLast(new Node(firstCoefficient - coefficientOfMatchingNode, firstPOX, firstPOY, firstPOZ));
                firstPolynomial.deleteFirst();
                if (coefficientOfMatchingNode != 0) {secondPolynomial.deleteFirst();}
            } else {
                result.insertLast(new Node(firstPolynomial.head.coefficient, firstPolynomial.head.powerOfX, firstPolynomial.head.powerOfY, firstPolynomial.head.powerOfZ));
                firstPolynomial.deleteFirst();
            }
        }
        if (!secondPolynomial.isEmpty()) {
            while (!secondPolynomial.isEmpty()) {
                result.insertLast(new Node(secondPolynomial.head.coefficient, secondPolynomial.head.powerOfX, secondPolynomial.head.powerOfY, secondPolynomial.head.powerOfZ));
                secondPolynomial.deleteFirst();
            }
        }
        return result;
    }


}