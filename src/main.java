import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("How many customers are in the queue?");
        int threads = input.nextInt();


        for (int i = 0; i < threads; i++){
            MultiThread myThing = new MultiThread(i);
            myThing.start();
            //myThing.join(); Needs a try catch around it but ensures that the next thread joins when the first is dead
            //myThing.isAlive() Returns a boolean for weather the thread is still running
        }
        //myThing.run(); //This runs the code, but not in its own thread.

    }

    action test = new action('w');

}

