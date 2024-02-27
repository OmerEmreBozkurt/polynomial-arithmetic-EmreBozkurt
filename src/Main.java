import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        readInputFile();
    }

    /*This method reads Input.txt file and imports polinomials to linkedList*/
    public static void readInputFile(){
        try{
            File myObj = new File("D:\\IdeaProjects\\polynomial-arithmetic-EmreBozkurt\\src\\Input.txt");
            Scanner reader = new Scanner(myObj);
            int numberOfOperations = Integer.parseInt(reader.next());
            LinkedList[] polynomials = new LinkedList[numberOfOperations];
            for (int i = 0; i < numberOfOperations; i++) {
                char operation = reader.next().charAt(0);
                String polynomial1 = reader.next();
                String polynomial2 = reader.next();
                polynomials[0] = polynomialStringToLinkedList(polynomial1);
                System.out.println(polynomialStringToLinkedList(polynomial1));
                System.out.println("Polynomial: " + polynomial1);
            }
        } catch (Exception e) {e.printStackTrace();}
    }

    /*This method creates a linkedlist from polynomial in String*/
    public static LinkedList polynomialStringToLinkedList(String Stringform){
        LinkedList linkedListForm = new LinkedList();
        String[] terms = Stringform.split("(?=[+-])");
        for (String term : terms) {
            int coefficient = 1, PoX = 0, PoY = 0, PoZ = 0;
            if (term.contains("x")) {
                int indexOfX = term.indexOf("x");
                if (indexOfX != 0) {
                    coefficient = Integer.parseInt(term.substring(0, indexOfX));
                }
                if (term.contains("y")) {
                    int indexOfY = term.indexOf("y");
                    if (indexOfX + 1 == indexOfY) {
                        PoX = 1;
                    } else {
                        PoX = Integer.parseInt(term.substring(indexOfX + 1, indexOfY));
                    }
                    if (term.contains("z")) {
                        int indexOfZ = term.indexOf("z");
                        if (indexOfY + 1 == indexOfZ) {
                            PoY = 1;
                        } else {
                            PoY = Integer.parseInt(term.substring(indexOfY + 1, indexOfZ));
                        }
                        if (indexOfZ == term.length() - 1) {
                            PoZ = 1;
                        } else {
                            PoZ = Integer.parseInt(term.substring(indexOfZ + 1));
                        }
                    } else {
                        if (indexOfY == term.length() - 1) {
                            PoY = 1;
                        } else {
                            PoY = Integer.parseInt(term.substring(indexOfY + 1));
                        }
                    }
                } else if (term.contains("z")) {
                    int indexOfZ = term.indexOf("z");
                    if (indexOfX + 1 == indexOfZ) {
                        PoX = 1;
                    } else {
                        if (indexOfX == term.length() - 1) {
                            PoX = 1;
                        } else {
                            PoX = Integer.parseInt(term.substring(indexOfX + 1, indexOfZ));
                        }
                    }
                    if (indexOfZ == term.length() - 1) {
                        PoZ = 1;
                    } else {
                        PoZ = Integer.parseInt(term.substring(indexOfZ + 1));
                    }
                } else {
                    if (indexOfX == term.length() - 1) {
                        PoX = 1;
                    } else {
                        PoX = Integer.parseInt(term.substring(indexOfX + 1));
                    }
                }
            } else if (term.contains("y")) {
                int indexOfY = term.indexOf("y");
                if (term.contains("z")) {
                    int indexOfZ = term.indexOf("z");
                    if (indexOfY + 1 == indexOfZ) {
                        PoY = 1;
                    } else {
                        PoY = Integer.parseInt(term.substring(indexOfY + 1, indexOfZ));
                    }
                    if (indexOfZ == term.length() - 1) {
                        PoZ = 1;
                    } else {
                        PoZ = Integer.parseInt(term.substring(indexOfZ + 1));
                    }
                } else {
                    if (indexOfY == term.length() - 1) {
                        PoY = 1;
                    } else {
                        PoY = Integer.parseInt(term.substring(indexOfY + 1));
                    }
                }
            } else if (term.contains("z")) {
                int indexOfZ = term.indexOf("z");
                if (indexOfZ == term.length() - 1) {
                    PoZ = 1;
                } else {
                    PoZ = Integer.parseInt(term.substring(indexOfZ + 1));
                }
            }
            Node testNode = new Node(coefficient, PoX, PoY, PoZ);
            linkedListForm.insertLast(testNode);
        }
        return linkedListForm;
    }

}
