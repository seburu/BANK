import java.util.Objects;

public class Controller {

    String[] names = {"Sebastian", "Marco", "Sofie", "Nick", "Mathilde"};
    Account[] accounts = new Account[names.length];

    //Creating actions arrays for each user
    Action[] actionsSeb = {
            new Action("Sebastian", "D", 0, ""),
            new Action("Sebastian", "W", 0, ""),
            new Action("Sebastian", "T", 0, names[(int) (Math.random() * 4)]),
            new Action("Sebastian", "B", 0, "")};

    Action[] actionsSofie = {
            new Action("Sofie", "D", 0, ""),
            new Action("Sofie", "W", 0, ""),
            new Action("Sofie", "T", 0, names[(int) (Math.random() * 4)]),
            new Action("Sofie", "B", 0, "")};

    Action[] actionsMarco = {
            new Action("Marco", "D", 0, ""),
            new Action("Marco", "W", 0, ""),
            new Action("Marco", "T", 0, names[(int) (Math.random() * 4)]),
            new Action("Marco", "B", 0, "")};

    Action[] actionsNick = {
            new Action("Nick", "D", 0, ""),
            new Action("Nick", "W", 0, ""),
            new Action("Nick", "T", 0, names[(int) (Math.random() * 4)]),
            new Action("Nick", "B", 0, "")};

    Action[] actionsMathilde = {
            new Action("Mathilde", "D", 0, ""),
            new Action("Mathilde", "W", 0, ""),
            new Action("Mathilde", "T", 0, names[(int) (Math.random() * 4)]),
            new Action("Mathilde", "B", 0, "")};

    //Initializes accounts
    public Account[] initializeAccounts() {
        //creates an account for each user.
        for (int i = 0; i < names.length; i++) {
            //All accounts are given a startvalue of 100 and a name from the global list.
            Account account = new Account(100, names[i]);
            accounts[i] = account;
        }
        return accounts;
    }

    //initializes and runs threads
    public void initializeThreads() {
        //Creates threads.
        MultiThread seb = new MultiThread(actionsSeb, this, false);
        MultiThread marco = new MultiThread(actionsMarco, this, false);
        MultiThread sof = new MultiThread(actionsSofie, this, false);
        MultiThread nick = new MultiThread(actionsNick, this, false);

        //To make the threads more interesting we have added a "slow thread"
        MultiThread math = new MultiThread(actionsMathilde, this, true);

        //Starts all threads
        seb.start();
        marco.start();
        sof.start();
        nick.start();
        math.start();
    }

    //method for finding account by its owners name.
    public Account findAccount(String name) {
        for (Account account : accounts) {
            if (Objects.equals(account.name, name)) {
                return account;
            }
        }
        return null;
    }

    //Withdraw amount from specific account.
    public void withdraw(Action action, Boolean isSlow) throws InterruptedException {
        //finds the account for the action
        Account currAcc = findAccount(action.id);

        //checks if resource is available before use and then occupies it.
        while (!currAcc.mutex.tryAcquire()) {
            //Thread sleeps before trying again to make it easier to optain resource.
            Thread.sleep(1);
        }

        //slow thread sleeps
        if (isSlow) {
            Thread.sleep(1000);
        }
        //withdraws amount
        currAcc.changeBalance(-action.amount);
        System.out.println("Withdrew " + action.amount + " from " + action.id);

        //makes resource available for other threads.
        currAcc.mutex.release();

    }

    public void deposit(Action action, Boolean isSlow) throws InterruptedException {
        Account currAcc = findAccount(action.id);

        while (!currAcc.mutex.tryAcquire()) {
            Thread.sleep(1);
        }

        if (isSlow) {
            Thread.sleep(1000);
        }

        //deposits amount
        currAcc.changeBalance(action.amount);
        System.out.println("Deposited " + action.amount + " to " + action.id);
        currAcc.mutex.release();

    }

    public void tranfer(Action action, Boolean isSlow) throws InterruptedException {
        //finds users account.
        Account currAcc = findAccount(action.id);
        //finds the tranfer receivers account.
        Account recAcc = findAccount(action.receiver);

        while (true) {
            //try to acquire first resource
            if (currAcc.mutex.tryAcquire()) {
                //try to acquire second resource
                if (recAcc.mutex.tryAcquire()) {
                    break;
                } else {
                    // To prevent deadlocks we release the first acquired resources
                    currAcc.mutex.release();
                }
            }
            Thread.sleep(1);
        }

        if (isSlow) {
            Thread.sleep(1000);
        }
        //checks if current user can afford the tranfer
        if (currAcc.changeBalance(-action.amount)) {
            //Tranfers money to receiver account
            recAcc.changeBalance(action.amount);
            System.out.println("Transfered " + action.amount + " to " + action.receiver + " from " + action.id);
        }

        currAcc.mutex.release();
        recAcc.mutex.release();
    }

    //checks balance
    public void balance(Action action, Boolean isSlow) throws InterruptedException {
        Account currAcc = findAccount(action.id);

        while (!currAcc.mutex.tryAcquire()) {
            Thread.sleep(1);
        }

        if (isSlow) {
            Thread.sleep(1000);
        }
        //checks / prints balance
        System.out.println("Current balance for " + action.id + " is: " + currAcc.balance);
        currAcc.mutex.release();
    }

    //redirects to method handling the specified action.
    public void doAction(Action action, Boolean isSlow) throws InterruptedException {
        String act = action.action;
        switch (act) {
            case "D" -> deposit(action, isSlow);
            case "W" -> withdraw(action, isSlow);
            case "T" -> tranfer(action, isSlow);
            case "B" -> balance(action, isSlow);
        }
    }

    public void start() {
        accounts = initializeAccounts();
        initializeThreads();
    }
}
