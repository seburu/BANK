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
        MultiThread seb = new MultiThread(actionsSeb, this,false);
        MultiThread marco = new MultiThread(actionsMarco, this,false);
        MultiThread sof = new MultiThread(actionsSofie,this,false);
        MultiThread nick = new MultiThread(actionsNick,this,false);
        MultiThread math = new MultiThread(actionsMathilde,this,true);

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

    public void withdraw(Action action, Boolean isSlow) throws InterruptedException {
        Account currAcc = findAccount(action.id);

        currAcc.mutex.acquire();

        if(isSlow){
            Thread.sleep(1000);
        }
        currAcc.changeBalance(-action.amount);
        System.out.println("Withdrew " + action.amount + " from " + action.id);
        currAcc.mutex.release();

    }

    public void deposit(Action action, Boolean isSlow) throws InterruptedException {
        Account currAcc = findAccount(action.id);

        currAcc.mutex.acquire();

        if(isSlow){
            Thread.sleep(1000);
        }
        currAcc.changeBalance(action.amount);
        System.out.println("Deposited " + action.amount + " to " + action.id);
        currAcc.mutex.release();

    }

    public void tranfer(Action action, Boolean isSlow) throws InterruptedException {
        Account currAcc = findAccount(action.id);
        Account recAcc = findAccount(action.receiver);

        currAcc.mutex.acquire();
        recAcc.mutex.acquire();

        if(isSlow){
            Thread.sleep(1000);
        }
        if(currAcc.changeBalance(-action.amount)){
            recAcc.changeBalance(action.amount);
            System.out.println("Transfered " + action.amount + " to " + action.receiver + " from " + action.id);
        }

        currAcc.mutex.release();
        recAcc.mutex.release();
    }

    public void balance(Action action, Boolean isSlow) throws InterruptedException {
        Account currAcc = findAccount(action.id);

        currAcc.mutex.acquire();

        if(isSlow){
            Thread.sleep(1000);
        }
        System.out.println("Current balance for " + action.id + " is: " + currAcc.balance);
        currAcc.mutex.release();
    }

    public void doAction(Action action, Boolean isSlow) throws InterruptedException {
        String act = action.action;
        switch (act) {
            case "D" -> deposit(action, isSlow);
            case "W" -> withdraw(action,isSlow);
            case "T" -> tranfer(action,isSlow);
            case "B" -> balance(action,isSlow);
        }
    }
    public void start(){
        accounts = initializeAccounts();
        initializeThreads();
    }

}