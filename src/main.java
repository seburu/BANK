import java.util.ArrayList;

public class main {



    int noOfUsers;
    String[] names = {"Sebastian","Marco","Sofie","Nick","Mathilde"};
    String[] action = {"D","W","T","B"};
    String[] inputs =    {names[0]+" "+action[0], names[1]+" "+action[0],names[2]+" "+action[0],names[3]+" "+action[0],names[4]+" "+action[0],names[0]+" "+action[1],names[1]+" "+action[1],names[2]+" "+action[1],names[3]+" "+action[1],
            names[4]+" "+action[1],names[0]+" "+action[2],names[1]+" "+action[2],names[2]+" "+action[2],names[3]+" "+action[2],names[4]+" "+action[2],names[0]+" "+action[3],names[1]+" "+action[3],names[2]+" "+action[3],names[3]+" "+action[3],names[4]+" "+action[3],
            names[0]+" "+action[0], names[1]+" "+action[0],names[2]+" "+action[0],names[3]+" "+action[0],names[4]+" "+action[0],names[0]+" "+action[1],names[1]+" "+action[1],names[2]+" "+action[1],names[3]+" "+action[1],
            names[4]+" "+action[1],names[0]+" "+action[2],names[1]+" "+action[2],names[2]+" "+action[2],names[3]+" "+action[2],names[4]+" "+action[2],names[0]+" "+action[3],names[1]+" "+action[3],names[2]+" "+action[3],names[3]+" "+action[3],names[4]+" "+action[3],
            names[0]+" "+action[0], names[1]+" "+action[0],names[2]+" "+action[0],names[3]+" "+action[0],names[4]+" "+action[0],names[0]+" "+action[1],names[1]+" "+action[1],names[2]+" "+action[1],names[3]+" "+action[1],
            names[4]+" "+action[1],names[0]+" "+action[2],names[1]+" "+action[2],names[2]+" "+action[2],names[3]+" "+action[2],names[4]+" "+action[2],names[0]+" "+action[3],names[1]+" "+action[3],names[2]+" "+action[3],names[3]+" "+action[3],names[4]+" "+action[3]};

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


}

