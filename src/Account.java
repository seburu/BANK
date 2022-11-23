import java.util.concurrent.Semaphore;

public class Account {
    int balance;
    String name;
    //boolean isInUse = false;
    Semaphore mutex = new Semaphore(1);

    public Account(int balance, String name){
        this.balance = balance;
        this.name = name;
    }

    //method used for changing balance.
    public Boolean changeBalance(int amount){
        if (this.balance + amount < 0){
            System.out.println(name + " do not have enough money to withdraw this amount");
            return false;
        }
        else{
            this.balance += amount;
        }
        return true;
    }
}
