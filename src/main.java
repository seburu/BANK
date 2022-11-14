import java.util.ArrayList;

public class main {



    int noOfUsers;
    String[] names = {"Sebastian","Marco","Sofie","Nick","Mathilde"};
    String[] action = {"D","W","T","B"};
    String[] inputs =    {names[0]+"D", names[1]+ "D",names[2]+ "D",names[3]+ "D",names[4]+ "D",names[0]+ "W",names[1]+ "W",names[2]+ "W",names[3]+ "W",
            names[4]+ "W",names[0]+ "T",names[1]+ "T",names[2]+ "T",names[3]+ "T",names[4]+ "T",names[0]+ "B",names[1]+ "B",names[2]+ "B",names[3]+ "B",names[4]+ "B",
            names[0]+"D", names[1]+ "D",names[2]+ "D",names[3]+ "D",names[4]+ "D",names[0]+ "W",names[1]+ "W",names[2]+ "W",names[3]+ "W",
            names[4]+ "W",names[0]+ "T",names[1]+ "T",names[2]+ "T",names[3]+ "T",names[4]+ "T",names[0]+ "B",names[1]+ "B",names[2]+ "B",names[3]+ "B",names[4]+ "B",
            names[0]+"D", names[1]+ "D",names[2]+ "D",names[3]+ "D",names[4]+ "D",names[0]+ "W",names[1]+ "W",names[2]+ "W",names[3]+ "W",
            names[4]+ "W",names[0]+ "T",names[1]+ "T",names[2]+ "T",names[3]+ "T",names[4]+ "T",names[0]+ "B",names[1]+ "B",names[2]+ "B",names[3]+ "B",names[4]+ "B"};

    public Account[] initializeAccounts(){
        noOfUsers = names.length;
        Account[] accounts = new Account[noOfUsers];

        for(int i=0; i<noOfUsers-1;i++){
            Account account = new Account(100,names[i]);
            accounts[i] = account;
        }
        return accounts;
    }

    public static void main(String[] args) {

        for (int i = 0; i < 5; i++){
            MultiThread myThing = new MultiThread(i);
            myThing.start();
            //myThing.join(); Needs a try catch around it but ensures that the next thread joins when the first is dead
            //myThing.isAlive() Returns a boolean for weather the thread is still running
        }
        //myThing.run(); //This runs the code, but not in its own thread.

    }

    action test = new action('w');

}

