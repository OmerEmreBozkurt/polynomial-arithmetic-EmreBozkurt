import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        File inputFile = new File("input.txt");
        File outputFile = new File("output.txt");
        readInputFileAndDoOperations(inputFile,outputFile);

        LinkedList test = polynomialStringToLinkedList("3xy3z+3x3+5z+4y+5yz+6x4z3");
        test.sortPolynomial();
        System.out.println(test);
    }

    /**
     * This method reads input file and does operations and writes results in output file
     * @param inputFile
     * @param outputFile
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
                System.out.println(result);
                writer.write(result + "\n");
            }
            writer.close();
        } catch (Exception e) {e.printStackTrace();}
    }

    /*This method creates a linkedlist from polynomial in String*/
    public static LinkedList polynomialStringToLinkedList(String Stringform){
        LinkedList linkedListForm = new LinkedList();
        String[] terms = Stringform.split("(?=[+-])");
        for (String term : terms) {     //this loop check every possibility, takes digits, creates nodes and inserts linkedList
            int coefficient = 1;
            int PowerOfX = 0;
            int PowerOfY = 0;
            int PowerOfZ = 0;
            if (term.contains("x")){
                int indexOfX = term.indexOf("x");
                if (indexOfX != 0) {
                    if (term.substring(0, indexOfX).equals("-")) {coefficient = -1;}
                    else if (!term.substring(0, indexOfX).equals("+")) {coefficient = Integer.parseInt(term.substring(0, indexOfX));}
                }
                if (term.contains("y")) {
                    int indexOfY = term.indexOf("y");
                    if (indexOfX + 1 == indexOfY) {PowerOfX = 1;}
                    else {PowerOfX = Integer.parseInt(term.substring(indexOfX + 1, indexOfY));}
                    if (term.contains("z")) {
                        int indexOfZ = term.indexOf("z");
                        if (indexOfY + 1 == indexOfZ) {PowerOfY = 1;}
                        else {PowerOfY = Integer.parseInt(term.substring(indexOfY + 1, indexOfZ));}
                        if (indexOfZ == term.length() - 1) {PowerOfZ = 1;}
                        else {PowerOfZ = Integer.parseInt(term.substring(indexOfZ + 1));}
                    } else if (indexOfY == term.length() - 1) {PowerOfY = 1;}
                    else {PowerOfY = Integer.parseInt(term.substring(indexOfY + 1));}
                } else if (term.contains("z")){
                    int indexOfZ = term.indexOf("z");
                    if (indexOfX + 1 == indexOfZ) {PowerOfX = 1;}
                    else {PowerOfX = Integer.parseInt(term.substring(indexOfX + 1, indexOfZ));}
                    if (indexOfZ == term.length() - 1) {PowerOfZ = 1;}
                    else {PowerOfZ = Integer.parseInt(term.substring(indexOfZ + 1));}
                } else if (indexOfX == term.length() - 1) {PowerOfX = 1;}
                else {PowerOfX = Integer.parseInt(term.substring(indexOfX + 1));}
            } else if (term.contains("y")) {
                int indexOfY = term.indexOf("y");
                if (indexOfY != 0) {
                    if (term.substring(0, indexOfY).equals("-")) {coefficient = -1;}
                    else if (!term.substring(0, indexOfY).equals("+")) {coefficient = Integer.parseInt(term.substring(0, indexOfY));}
                }
                if (term.contains("z")) {
                    int indexOfZ = term.indexOf("z");
                    if (indexOfY + 1 == indexOfZ) {PowerOfY = 1;}
                    else {PowerOfY = Integer.parseInt(term.substring(indexOfY + 1, indexOfZ));}
                    if (indexOfZ == term.length() - 1) {PowerOfZ = 1;}
                    else {PowerOfZ = Integer.parseInt(term.substring(indexOfZ + 1));}
                } else if (indexOfY == term.length() - 1) {PowerOfY = 1;}
                else {PowerOfY = Integer.parseInt(term.substring(indexOfY + 1));}
            } else if (term.contains("z")) {
                int indexOfZ = term.indexOf("z");
                if (indexOfZ != 0) {
                    if (term.substring(0, indexOfZ).equals("-")) {coefficient = -1;}
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
