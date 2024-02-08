// This is a starter file for QuestionsGame.
//
// You should delete this comment and replace it with your class
// header comment.

import java.io.PrintStream;
import java.util.Scanner;

public class QuestionsGame {
    // Your code here
    public QuestionNode overallRoot;

    public static class QuestionNode {
        // Your code here
        public String data;//should be the question we can change it later tho
        public QuestionNode left;//otherwise yes
        public QuestionNode right;//otherwise no

        public QuestionNode(String data){   
            //default constructor
            this(data, null, null);
        }

        public QuestionNode(String data, QuestionNode left, QuestionNode right){
            //ads left and right
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }
    //adds new object  to root
    public QuestionsGame(String object) {//ben
        overallRoot = new QuestionNode(object);

    }

    public QuestionsGame(Scanner input) {//creates the tree out of a file
        overallRoot = addBranch(input);//call the recursive function on the overall root
    }

    private QuestionNode addBranch(Scanner input){//recursive call to create the tree
        if(!input.hasNextLine()){//check if the file continues.
            return null;//end recursive call
        } else if(input.nextLine().charAt(0) == 'Q'){//check if the next information is a question.
            return new QuestionNode(input.nextLine(), addBranch(input), addBranch(input));//create the node with the information and continue on left and right.
        } else {//if the information is an answer
            return new QuestionNode(input.nextLine());//create node with only information and no children
        }
    }

    public void saveQuestions(PrintStream output) {//ben
        //if the printStream is null end
        if(output==null){
            throw new IllegalArgumentException();
        }
        //if it isnt null, start adding the tree to the printStream
        printQuest(output, overallRoot);
    }

    //prints the tree into a printStream
    public Boolean printQuest(PrintStream output, QuestionNode curNode){
        //if it has 2 kids
        if(curNode.left!=null||curNode.right!=null){
            //print in question and its data
            output.println("Q: ");
            output.println(curNode.data);
            //go left and right and recur
            return printQuest(output, curNode.left) && printQuest(output, curNode.right);
        }
        else{
            //if it doesnt have to kids, means it will have 0
            //print answer
            output.println("A: ");
            output.println(curNode.data);
            //end code
            return true;
        }
    }


    public void play() {//alvin
        Scanner sc = new Scanner(System.in);//create scanner
        QuestionNode curNode = overallRoot;//temp node
        while(curNode != null){//go through tree until end
            //prints question and gets answer
            System.out.println(curNode.data + "\n");
            String answer = sc.nextLine();
            if(curNode.left.left == null && answer.charAt(0) == 'y'){//if answer was yes
                //checks to see if it got the obect right
                System.out.println("I guess that your object is " + curNode.left.data + "\nAm I right? ");
                String confirmation = sc.nextLine();
                //if it did print i win and end code
                if(confirmation.charAt(0) == 'y'){
                    System.out.println("Awesome! I win!");
                    break;
                //if it didnt
                } else {
                    //ask for new object and save in newObject
                    System.out.println("Boo! I lose. Please help me get better!\nWhat is your object? ");
                    String newObject = sc.nextLine();
                    //ask for question and save in newQuestion
                    System.out.println("Please give me a yes/no question that distinguishes between " +  curNode.left.data + " and " + newObject + ".");
                    String newQuestion = sc.nextLine();
                    //ask if you go left or right for the quesion
                    System.out.println("Is the answer \"yes\" for " + newObject + "? (y/n)? ");
                    String questionAnswer = sc.nextLine();
                    //if yes add new node to the left of tree
                    if(questionAnswer.charAt(0) == 'y'){
                        curNode.left = new QuestionNode(newQuestion, new QuestionNode(newObject), curNode.left);
                    //if no also add new node to the right of tree
                    } else {
                        curNode.left = new QuestionNode(newQuestion, curNode.left, new QuestionNode(newObject));
                    }
                }
                //if the rights left is null
            } else if(curNode.right.left == null){
                //checks to see if it got the object right
                System.out.println("I guess that your object is " + curNode.right.data + "\nAm I right? ");
                String confirmation = sc.nextLine();
                //if it did print i win and end code
                if(confirmation.charAt(0) == 'y'){
                    System.out.println("Awesome! I win!");
                    break;
                //if it didnt 
                } else {
                    //ask for new object and save in newObject
                    System.out.println("Boo! I lose. Please help me get better!\nWhat is your object? ");
                    String newObject = sc.nextLine();
                    //ask for question and save in newQuestion
                    System.out.println("Please give me a yes/no question that distinguishes between " +  curNode.right.data + " and " + newObject + ".");
                    String newQuestion = sc.nextLine();
                    //ask if you go left or right for the question
                    System.out.println("Is the answer \"yes\" for " + newObject + "? (y/n)? ");
                    String questionAnswer = sc.nextLine();
                    //if yes add new node to left of tree
                    if(questionAnswer.charAt(0) == 'y'){
                        curNode.right = new QuestionNode(newQuestion, new QuestionNode(newObject), curNode.right);
                        //if no also add new node to right of tree
                    } else {
                        curNode.left = new QuestionNode(newQuestion, curNode.right, new QuestionNode(newObject));
                    }
                }
                //if its yes go to the left
            } else if(answer.charAt(0) == 'y'){
                curNode = curNode.left;
                //if its no then go to the right
            } else {
                curNode = curNode.right;
            }
        }
    }
}
