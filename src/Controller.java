import java.util.ArrayList;
import java.util.Objects;

public class Controller {

    int noOfUsers;
    String[] names = {"Sebastian","Marco","Sofie","Nick","Mathilde"};
    String[] action = {"D","W","T","B"};
    Account[] accounts = new Account[names.length];

    Action[] actionsSeb = {new Action("Sebastian","D",1000,""),
            new Action("Sebastian","D",500,""),
            new Action("Sebastian","D",100,""),
            new Action("Sebastian","W",10,""),
            new Action("Sebastian","W",100,""),
            new Action("Sebastian","W",400,""),
            new Action("Sebastian","T",10,names[(int) (Math.random()*4)]),
            new Action("Sebastian","T",100,names[(int) (Math.random()*4)]),
            new Action("Sebastian","T",200,names[(int) (Math.random()*4)]),
            new Action("Sebastian","B",10,"")};

    Action[] actionsSofie = {new Action("Sofie","D",1000,""),
            new Action("Sofie","D",500,""),
            new Action("Sofie","D",100,""),
            new Action("Sofie","W",10,""),
            new Action("Sofie","W",100,""),
            new Action("Sofie","W",400,""),
            new Action("Sofie","T",10,names[(int) (Math.random()*4)]),
            new Action("Sofie","T",100,names[(int) (Math.random()*4)]),
            new Action("Sofie","T",200,names[(int) (Math.random()*4)]),
            new Action("Sofie","B",10,"")};

    Action[] actionsMarco = {new Action("Marco","D",1000,""),
            new Action("Marco","D",500,""),
            new Action("Marco","D",100,""),
            new Action("Marco","W",10,""),
            new Action("Marco","W",100,""),
            new Action("Marco","W",400,""),
            new Action("Marco","T",10,names[(int) (Math.random()*4)]),
            new Action("Marco","T",100,names[(int) (Math.random()*4)]),
            new Action("Marco","T",200,names[(int) (Math.random()*4)]),
            new Action("Marco","B",10,"")};

    Action[] actionsNick = {new Action("Nick","D",1000,""),
            new Action("Nick","D",500,""),
            new Action("Nick","D",100,""),
            new Action("Nick","W",10,""),
            new Action("Nick","W",100,""),
            new Action("Nick","W",400,""),
            new Action("Nick","T",10,names[(int) (Math.random()*4)]),
            new Action("Nick","T",100,names[(int) (Math.random()*4)]),
            new Action("Nick","T",200,names[(int) (Math.random()*4)]),
            new Action("Nick","B",10,"")};

    Action[] actionsMathilde = {new Action("Mathilde","D",1000,""),
            new Action("Mathilde","D",500,""),
            new Action("Mathilde","D",100,""),
            new Action("Mathilde","W",10,""),
            new Action("Mathilde","W",100,""),
            new Action("Mathilde","W",400,""),
            new Action("Mathilde","T",10,names[(int) (Math.random()*4)]),
            new Action("Mathilde","T",100,names[(int) (Math.random()*4)]),
            new Action("Mathilde","T",200,names[(int) (Math.random()*4)]),
            new Action("Mathilde","B",10,"")};

    Action[] testAct = { new Action("Sebastian","T",100,"Marco"),
            new Action("Sebastian","T",100,"Marco"),
            new Action("Sebastian","T",100,"Marco"),
            new Action("Sebastian","T",100,"Marco"),
            new Action("Sebastian","T",100,"Marco"),
            new Action("Sebastian","T",100,"Marco"),
            new Action("Sebastian","T",100,"Marco"),
            new Action("Sebastian","T",100,"Marco"),
            new Action("Sebastian","T",100,"Marco")
    };
    Action[] testAct2 = { new Action("Sofie","T",100,"Marco"),
            new Action("Sofie","T",100,"Marco"),
            new Action("Sofie","T",100,"Marco"),
            new Action("Sofie","T",100,"Marco"),
            new Action("Sofie","T",100,"Marco"),
            new Action("Sofie","T",100,"Marco"),
            new Action("Sofie","T",100,"Marco"),
            new Action("Sofie","T",100,"Marco"),
            new Action("Sofie","T",100,"Marco")
    };


    String[] seb = {names[0]+" "+action[0]+" 10000", names[0]+" "+action[0]+" 200",names[0]+" "+action[0],names[0]+" "+action[0],names[0]+" "+action[0]};

    public Account[] initializeAccounts(){
        noOfUsers = names.length;

        for(int i=0; i<noOfUsers-1;i++){
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

        MultiThread test1 = new MultiThread(testAct,this);
        MultiThread test2 = new MultiThread(testAct2,this);

        test1.start();
        test2.start();

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
        System.out.println("Withdrew " + action.amount + " from " + action.id);
        currAcc.isInUse = false;
    }

    public void deposit(Action action){
        Account currAcc = findAccount(action.id);
        while(currAcc.isInUse){
            System.out.println("loading...");
        }
        currAcc.isInUse = true;
        currAcc.changeBalance(action.amount);
        System.out.println("Deposited " + action.amount + " to " + action.id);
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

        if(currAcc.changeBalance(-action.amount)){
            recAcc.changeBalance(action.amount);
            System.out.println("Transfered " + action.amount + " to " + action.receiver + " from " + action.id);
        }

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

    public void doAction(Action action){
        String act = action.action;
        switch (act){
            case "D" :
                deposit(action);
                break;
            case "W" :
                withdraw(action);
                break;
            case "T" :
                tranfer(action);
                break;
            case "B" :
                balance(action);
                break;
        }
    }
    public void start(){
        accounts = initializeAccounts();
        initializeThreads();
    }

}

