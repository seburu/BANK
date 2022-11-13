public class MultiThread extends Thread {

    private int threadNumber;

    public MultiThread (int threadNumber){ // Constructor
        this.threadNumber = threadNumber;

    }

    @Override
    public void run(){
        for (int i = 1; i <= 5; i++){
            System.out.println(i + " From thread number" + threadNumber);

            try {
                Thread.sleep(1); // Pause for 1 second between each step in the loop
            } catch (InterruptedException e) {
                //e.printStackTrace();
            }
        }

    }

}
