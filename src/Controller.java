import java.util.ArrayList;
import java.util.Objects;

public class Controller {

    int noOfUsers;
    String[] names = {"Sebastian","Marco","Sofie","Nick","Mathilde"};
    String[] action = {"D","W","T","B"};
    Account[] accounts = new Account[names.length];

    Action[] actionsSeb = {new Action("Sebastian","D",1000,""),
                        new Action("Sebastian","W",10,""),
                        new Action("Sebastian","B",0,""),
                        new Action("Sebastian","T",100,"Marco"),
                        new Action("Sebastian","D",1000,""),
                        new Action("Sebastian","W",10,""),
                        new Action("Sebastian","B",0,""),
                        new Action("Sebastian","T",100,"Marco")};
    Action[] actionsSofie = {new Action("Sofie","D",1000,""),
            new Action("Sofie","W",10,"")};
    Action[] actionsMarco = {new Action("Marco","D",1000,""),
            new Action("Marco","W",10,"")};
    Action[] actionsNick = {new Action("Nick","D",1000,""),
            new Action("Nick","W",10,"")};
    Action[] actionsMathilde = {new Action("Mathilde","D",1000,""),
            new Action("Mathilde","W",10,"")};


    String[] seb = {names[0]+" "+action[0]+" 10000", names[0]+" "+action[0]+" 200",names[0]+" "+action[0],names[0]+" "+action[0],names[0]+" "+action[0]};

    public Account[] initializeAccounts(){
        noOfUsers = names.length;

        for(int i=0; i<noOfUsers-1;i++){
            Account account = new Account(100,names[i]);
            accounts[i] = account;
        }
        return accounts;
    }

    public Account[] getAccounts() {
        return accounts;
    }

    public void initializeThreads(){
        MultiThread seb = new MultiThread(actionsSeb);
        MultiThread marco = new MultiThread(actionsMarco);
        MultiThread sof = new MultiThread(actionsSofie);
        MultiThread nick = new MultiThread(actionsNick);
        MultiThread math = new MultiThread(actionsMathilde);

        seb.start();
        marco.start();
        sof.start();
        nick.start();
        math.start();
    }

    public Account findAccount(String name){
        for (Account account : accounts) {
            if (Objects.equals(account.name, name)) {
                return account;
            }
        }
        return null;
    }

    public void withdraw(Action action){
        Account currAcc = findAccount(action.id);
        while(currAcc.isInUse){
            System.out.println("loading...");
        }
        currAcc.isInUse = true;
        currAcc.changeBalance(-action.amount);
        currAcc.isInUse = false;
    }

    public void deposit(Action action){
        Account currAcc = findAccount(action.id);
        while(currAcc.isInUse){
            System.out.println("loading...");
        }
        currAcc.isInUse = true;
        currAcc.changeBalance(action.amount);
        currAcc.isInUse = false;
    }

    public void tranfer(Action action){
        Account currAcc = findAccount(action.id);
        Account recAcc = findAccount(action.receiver);

        while(currAcc.isInUse || recAcc.isInUse){
            System.out.println("loading...");
        }
        currAcc.isInUse = true;
        recAcc.isInUse = true;

        currAcc.changeBalance(-action.amount);
        recAcc.changeBalance(action.amount);

        currAcc.isInUse = false;
        recAcc.isInUse = false;
    }

    public void balance(Action action){
        Account currAcc = findAccount(action.id);

        while(currAcc.isInUse){
            System.out.println("loading...");
        }

        System.out.println("Current balance for " + action.id + " is: " + currAcc.balance);
    }

    public void doAction(){

    }

    public static void main(String[] args) {

        for (int i = 0; i < 5; i++){
            //MultiThread myThing = new MultiThread(i);
            //myThing.start();
            //myThing.join(); Needs a try catch around it but ensures that the next thread joins when the first is dead
            //myThing.isAlive() Returns a boolean for weather the thread is still running
        }
        //myThing.run(); //This runs the code, but not in its own thread.

    }


}

