import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        File inputFile = new File("input.txt");
        File outputFile = new File("output.txt");
        readInputFileAndDoOperations(inputFile,outputFile);
    }

    /**
     * This method reads input file and does operations and writes results in output file
     * @param inputFile file which hold input data (location given in main)
     * @param outputFile file which hold output data (location given in main)
     */
    public static void readInputFileAndDoOperations(File inputFile, File outputFile){
        try{
            Scanner reader = new Scanner(inputFile);
            int numberOfOperations = Integer.parseInt(reader.next());
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
            for (int i = 0; i < numberOfOperations; i++) {
                char operator = reader.next().charAt(0);
                LinkedList polynomial1 = polynomialStringToLinkedList(reader.next());
                LinkedList polynomial2 = polynomialStringToLinkedList(reader.next());
                LinkedList result = Operations.doOperation(operator, polynomial1, polynomial2);
                writer.write(result + "\n");
            }
            writer.close();
        } catch (Exception e) {e.printStackTrace();}
    }

    /**
     * This method creates a linked-list from polynomial in String
     * It checks every condition of existence of "x", "y" and "z" in every term
     * @param Stringform input of polynomial in string
     * @return returns a linked list which contains polynomial
     */
    public static LinkedList polynomialStringToLinkedList(String Stringform){
        LinkedList linkedListForm = new LinkedList();
        String[] terms = Stringform.split("(?=[+-])");
        for (String term : terms) {     //this loop check every possibility, takes digits, creates nodes and inserts linkedList
            int coefficient = 1;
            int PowerOfX = 0;
            int PowerOfY = 0;
            int PowerOfZ = 0;
            if (term.contains("x")){ // conditions that term has "x"
                int indexOfX = term.indexOf("x");
                if (indexOfX != 0) {
                    if  (term.substring(0, indexOfX).equals("-")) {coefficient = -1;}
                    else if (!term.substring(0, indexOfX).equals("+")) {coefficient = Integer.parseInt(term.substring(0, indexOfX));}
                }
                if (term.contains("y")) { // conditions that term has "x" and "y"
                    int indexOfY = term.indexOf("y");
                    if (indexOfX + 1 == indexOfY) {PowerOfX = 1;}
                    else {PowerOfX = Integer.parseInt(term.substring(indexOfX + 1, indexOfY));}
                    if (term.contains("z")) {  // conditions that term has "x", "y" and "z"
                        int indexOfZ = term.indexOf("z");
                        if (indexOfY + 1 == indexOfZ) {PowerOfY = 1;}
                        else {PowerOfY = Integer.parseInt(term.substring(indexOfY + 1, indexOfZ));}
                        if (indexOfZ == term.length() - 1) {PowerOfZ = 1;}
                        else {PowerOfZ = Integer.parseInt(term.substring(indexOfZ + 1));}
                    } else if (indexOfY == term.length() - 1) {PowerOfY = 1;}  // condition that term has only "x" and "y"
                    else {PowerOfY = Integer.parseInt(term.substring(indexOfY + 1));}
                } else if (term.contains("z")){  // conditions that term has only "x" and "z"
                    int indexOfZ = term.indexOf("z");
                    if (indexOfX + 1 == indexOfZ) {PowerOfX = 1;}
                    else {PowerOfX = Integer.parseInt(term.substring(indexOfX + 1, indexOfZ));}
                    if (indexOfZ == term.length() - 1) {PowerOfZ = 1;}
                    else {PowerOfZ = Integer.parseInt(term.substring(indexOfZ + 1));}
                } else if (indexOfX == term.length() - 1) {PowerOfX = 1;}
                else {PowerOfX = Integer.parseInt(term.substring(indexOfX + 1));} // conditions that term has only "x"
            } else if (term.contains("y")) { // conditions that term has "y"; has not "x"
                int indexOfY = term.indexOf("y");
                if (indexOfY != 0) {
                    if  (term.substring(0, indexOfY).equals("-")) {coefficient = -1;}
                    else if (!term.substring(0, indexOfY).equals("+")) {coefficient = Integer.parseInt(term.substring(0, indexOfY));}
                }
                if (term.contains("z")) { // conditions that term has "y" and "z"; has not "x"
                    int indexOfZ = term.indexOf("z");
                    if (indexOfY + 1 == indexOfZ) {PowerOfY = 1;}
                    else {PowerOfY = Integer.parseInt(term.substring(indexOfY + 1, indexOfZ));}
                    if (indexOfZ == term.length() - 1) {PowerOfZ = 1;}
                    else {PowerOfZ = Integer.parseInt(term.substring(indexOfZ + 1));}
                } else if (indexOfY == term.length() - 1) {PowerOfY = 1;}  // conditions that term has only "y"; has not "x" and "z"
                else {PowerOfY = Integer.parseInt(term.substring(indexOfY + 1));}
            } else if (term.contains("z")) { // conditions that term has "z"; has not "x" and "y"
                int indexOfZ = term.indexOf("z");
                if (indexOfZ != 0) {
                    if  (term.substring(0, indexOfZ).equals("-")) {coefficient = -1;}
                    else if (!term.substring(0, indexOfZ).equals("+")) {coefficient = Integer.parseInt(term.substring(0, indexOfZ));}
                }
                if (indexOfZ == term.length() - 1) {PowerOfZ = 1;}
                else {PowerOfZ = Integer.parseInt(term.substring(indexOfZ + 1));}
            }
            linkedListForm.insertLast(new Node(coefficient,PowerOfX,PowerOfY,PowerOfZ));
        }
        return linkedListForm;
    }

}
