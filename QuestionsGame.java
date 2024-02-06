// This is a starter file for QuestionsGame.
//
// You should delete this comment and replace it with your class
// header comment.

import java.io.PrintStream;
import java.util.Scanner;

public class QuestionsGame {
    // Your code here
    QuestionNode overallQuestion;

    private static class QuestionNode {
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
        overallQuestion = new QuestionNode(object);

    }

    public QuestionsGame(Scanner input) {//alvin
        this(input.nextLine());
        addBranch(input, overallQuestion);
    }

    private Boolean addBranch(Scanner input, QuestionNode curNode){
        if(!input.hasNextLine()){
            return true;
        } else if(input.nextLine().charAt(0) == 'Q'){
            curNode.left = new QuestionNode(input.nextLine());
            return addBranch(input, curNode.left);
        } else {
            curNode.left = new QuestionNode(input.nextLine());
            if(input.nextLine().charAt(0) == 'A'){
                curNode.right = new QuestionNode(input.nextLine());
                return true;
            } else {
                curNode.right = new QuestionNode(input.nextLine());
                return addBranch(input, curNode.right);
            }
        }
    }

    public void saveQuestions(PrintStream output) {//ben
        if(output==null){
            throw new IllegalArgumentException();
        }
        printQuest(output, overallQuestion);
    }

    public Boolean printQuest(PrintStream output, QuestionNode curNode){
        if(curNode.left!=null||curNode.right!=null){
            output.println("Q: ");
            output.println(curNode.data);
        }
        else if(curNode.left==null&&curNode.right==null){
            output.println("A: ");
            output.println(curNode.data);
        }
        if(curNode.left != null){
            return printQuest(output, curNode.left);
        }
        else if(curNode.right != null){
            return printQuest(output, curNode.right);
        }
        else{
            return true;
        }
    }


    public void play() {//alvin
        
        while(curNode != null){

        }
    }
}
