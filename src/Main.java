import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        File myObj = new File("D:\\IdeaProjects\\polynomial-arithmetic-EmreBozkurt\\src\\Input.txt");
        readInputFile(myObj);
    }

    /*This method reads Input.txt file and calls operations*/
    public static void readInputFile(File file){
        try{
            Scanner reader = new Scanner(file);
            int numberOfOperations = Integer.parseInt(reader.next());
            LinkedList polynomials[] = new LinkedList[2];
            for (int i = 0; i < numberOfOperations; i++) {
                char operator = reader.next().charAt(0);
                String polynomial1 = reader.next();
                String polynomial2 = reader.next();
                polynomials[0] = polynomialStringToLinkedList(polynomial1);
                polynomials[1] = polynomialStringToLinkedList(polynomial2);
                LinkedList result = Operations.doOperation(operator, polynomials);
                System.out.println(result);
            }
        } catch (Exception e) {e.printStackTrace();}
    }

    /*This method creates a linkedlist from polynomial in String*/
    public static LinkedList polynomialStringToLinkedList(String Stringform){
        LinkedList linkedListForm = new LinkedList();
        String terms[] = Stringform.split("(?=[+-])");
        for (String term : terms) {     //this loop check every possibility, takes digits, creates nodes and inserts linkedList
            int coefficient = 1;
            int PowerOfX = 0;
            int PowerOfY = 0;
            int PowerOfZ = 0;
            if (term.contains("x")){
                int indexOfX = term.indexOf("x");
                if (indexOfX != 0) {coefficient = Integer.parseInt(term.substring(0, indexOfX));}
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
                if (indexOfZ == term.length() - 1) {PowerOfZ = 1;}
                else {PowerOfZ = Integer.parseInt(term.substring(indexOfZ + 1));}
            }
            linkedListForm.insertLast(new Node(coefficient,PowerOfX,PowerOfY,PowerOfZ));
        }
        return linkedListForm;
    }

}
