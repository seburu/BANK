public class Account {
    int balance;
    String name;
    boolean isInUse = false;

    public Account(int balance, String name){
        this.balance = balance;
        this.name = name;
    }

    public void changeBalance(int amount){
        if (this.balance + amount < 0){
            System.out.println("You do not have enough money to withdraw this amount");
        }
        else{
            this.balance += amount;
        }
    }
}
