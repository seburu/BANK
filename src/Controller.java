import java.util.ArrayList;
import java.util.Objects;

public class Controller {

    int noOfUsers;
    String[] names = {"Sebastian","Marco","Sofie","Nick","Mathilde"};
    String[] action = {"D","W","T","B"};
    Account[] accounts = new Account[names.length];

    Action[] actionsSeb = {
            new Action("Sebastian","D",0,""),
            new Action("Sebastian","W", 0,""),
            new Action("Sebastian","T",0,names[(int) (Math.random()*4)]),
            new Action("Sebastian","B",0,"")};

    Action[] actionsSofie = {
            new Action("Sofie","D",0,""),
            new Action("Sofie","W", 0,""),
            new Action("Sofie","T",0,names[(int) (Math.random()*4)]),
            new Action("Sofie","B",0,"")};

    Action[] actionsMarco = {
            new Action("Marco","D",0,""),
            new Action("Marco","W", 0,""),
            new Action("Mathilde","T",0,names[(int) (Math.random()*4)]),
            new Action("Mathilde","B",0,"")};

    Action[] actionsNick = {
            new Action("Mathilde","D",0,""),
            new Action("Mathilde","W", 0,""),
            new Action("Mathilde","T",0,names[(int) (Math.random()*4)]),
            new Action("Mathilde","B",0,"")};

    Action[] actionsMathilde = {
            new Action("Mathilde","D",0,""),
            new Action("Mathilde","W",0,""),
            new Action("Mathilde","T",0,names[(int) (Math.random()*4)]),
            new Action("Mathilde","B",0,"")};


    public Account[] initializeAccounts(){
        noOfUsers = names.length;

        for(int i=0; i<noOfUsers;i++){
            Account account = new Account(100,names[i]);
            accounts[i] = account;
        }
        return accounts;
    }

    //public Account[] getAccounts() {return accounts;}

    public void initializeThreads(){
        MultiThread seb = new MultiThread(actionsSeb, this);
        MultiThread marco = new MultiThread(actionsMarco, this);
        MultiThread sof = new MultiThread(actionsSofie,this);
        MultiThread nick = new MultiThread(actionsNick,this);
        MultiThread math = new MultiThread(actionsMathilde,this);

        /*MultiThread test1 = new MultiThread(testAct,this);
        MultiThread test2 = new MultiThread(testAct2,this);

        test1.start();
        test2.start();*/

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
        /*while(currAcc.isInUse){
            System.out.println("loading...");
        }*/
        //currAcc.isInUse = true;
        try {
            currAcc.mutex.acquire();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        currAcc.changeBalance(-action.amount);
        System.out.println("Withdrew " + action.amount + " from " + action.id);
        currAcc.mutex.release();

        //currAcc.isInUse = false;
    }

    public void deposit(Action action){
        Account currAcc = findAccount(action.id);
        /*while(currAcc.isInUse){
            System.out.println("loading...");
        }*/
        //currAcc.isInUse = true;
        try {
            currAcc.mutex.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        currAcc.changeBalance(action.amount);
        System.out.println("Deposited " + action.amount + " to " + action.id);
        currAcc.mutex.release();

        //currAcc.isInUse = false;
    }

    public void tranfer(Action action){
        Account currAcc = findAccount(action.id);
        Account recAcc = findAccount(action.receiver);
        /*
        while(currAcc.isInUse || recAcc.isInUse){
            System.out.println("loading...");
        }
        currAcc.isInUse = true;
        recAcc.isInUse = true;
        */
        try {
            currAcc.mutex.acquire();
            recAcc.mutex.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(currAcc.changeBalance(-action.amount)){
            recAcc.changeBalance(action.amount);
            System.out.println("Transfered " + action.amount + " to " + action.receiver + " from " + action.id);
        }

        currAcc.mutex.release();
        recAcc.mutex.release();

        //currAcc.isInUse = false;
        //recAcc.isInUse = false;
    }

    public void balance(Action action){
        Account currAcc = findAccount(action.id);

        /*while(currAcc.isInUse){
            System.out.println("loading...");
        }*/
        try {
            currAcc.mutex.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Current balance for " + action.id + " is: " + currAcc.balance);
        currAcc.mutex.release();

    }

    public void doAction(Action action){
        String act = action.action;
        switch (act) {
            case "D" -> deposit(action);
            case "W" -> withdraw(action);
            case "T" -> tranfer(action);
            case "B" -> balance(action);
        }
    }
    public void start(){
        accounts = initializeAccounts();
        initializeThreads();
    }

}

