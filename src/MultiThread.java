
public class MultiThread extends Thread {

    Action[] actions;

    public MultiThread (Action[] actions){ // Constructor
        this.actions = actions;
    }



    @Override
    public void run(){

        //Account userAcc = main.getAccounts;

        for (int i = 1; i <= 5; i++){
            //System.out.println(i + " From thread number" + threadNumber);

            try {
                Thread.sleep(1); // Pause for 1 second between each step in the loop
            } catch (InterruptedException e) {
                //e.printStackTrace();
            }
        }

    }

}
