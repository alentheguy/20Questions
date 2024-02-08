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
            this(data, null, null);
        }

        public QuestionNode(String data, QuestionNode left, QuestionNode right){
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    public QuestionsGame(String object) {//ben
        overallRoot = new QuestionNode(object);

    }

    public QuestionsGame(Scanner input) {//alvin
        overallRoot = addBranch(input);
    }

    private QuestionNode addBranch(Scanner input){
        if(!input.hasNextLine()){
            return null;
        } else if(input.nextLine().charAt(0) == 'Q'){
            return new QuestionNode(input.nextLine(), addBranch(input), addBranch(input));
        } else {
            return new QuestionNode(input.nextLine());
        }
    }

    public void saveQuestions(PrintStream output) {//ben
        if(output==null){
            throw new IllegalArgumentException();
        }
        printQuest(output, overallRoot);
    }

    public Boolean printQuest(PrintStream output, QuestionNode curNode){
        if(curNode.left!=null||curNode.right!=null){
            output.println("Q: ");
            output.println(curNode.data);
            return printQuest(output, curNode.left) && printQuest(output, curNode.right);
        }
        else{
            output.println("A: ");
            output.println(curNode.data);
            return true;
        }
    }


    public void play() {//alvin
        Scanner sc = new Scanner(System.in);
        QuestionNode curNode = overallRoot;
        while(curNode != null){
            System.out.println(curNode.data + "\n");
            String answer = sc.nextLine();
            if(curNode.left.left == null && answer.charAt(0) == 'y'){
                System.out.println("I guess that your object is " + curNode.left.data + "\nAm I right? ");
                String confirmation = sc.nextLine();
                if(confirmation.charAt(0) == 'y'){
                    System.out.println("Awesome! I win!");
                    break;
                } else {
                    System.out.println("Boo! I lose. Please help me get better!\nWhat is your object? ");
                    String newObject = sc.nextLine();
                    System.out.println("Please give me a yes/no question that distinguishes between " +  curNode.left.data + " and " + newObject + ".");
                    String newQuestion = sc.nextLine();
                    System.out.println("Is the answer \"yes\" for " + newObject + "? (y/n)? ");
                    String questionAnswer = sc.nextLine();
                    if(questionAnswer.charAt(0) == 'y'){
                        curNode.left = new QuestionNode(newQuestion, new QuestionNode(newObject), curNode.left);
                    } else {
                        curNode.left = new QuestionNode(newQuestion, curNode.left, new QuestionNode(newObject));
                    }
                }
            } else if(curNode.right.left == null){
                System.out.println("I guess that your object is " + curNode.right.data + "\nAm I right? ");
                String confirmation = sc.nextLine();
                if(confirmation.charAt(0) == 'y'){
                    System.out.println("Awesome! I win!");
                    break;
                } else {
                    System.out.println("Boo! I lose. Please help me get better!\nWhat is your object? ");
                    String newObject = sc.nextLine();
                    System.out.println("Please give me a yes/no question that distinguishes between " +  curNode.right.data + " and " + newObject + ".");
                    String newQuestion = sc.nextLine();
                    System.out.println("Is the answer \"yes\" for " + newObject + "? (y/n)? ");
                    String questionAnswer = sc.nextLine();
                    if(questionAnswer.charAt(0) == 'y'){
                        curNode.right = new QuestionNode(newQuestion, new QuestionNode(newObject), curNode.right);
                    } else {
                        curNode.left = new QuestionNode(newQuestion, curNode.right, new QuestionNode(newObject));
                    }
                }
            } else if(answer.charAt(0) == 'y'){
                curNode = curNode.left;
            } else {
                curNode = curNode.right;
            }
        }
    }
}
