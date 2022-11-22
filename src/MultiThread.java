
public class MultiThread extends Thread {

    Action[] actions;
    Controller con;
    Boolean isSlow;


    public MultiThread (Action[] actions, Controller con, boolean isSlow){ // Constructor
        this.actions = actions;
        this.con = con;
        this.isSlow = isSlow;
    }

    @Override
    public void run(){
        //doAction i forloop

        for (int i = 0; i < 20; i++) {
            int random = (int)(Math.random()*actions.length);
            actions[random].amount = (int) (Math.random() * 490)+10;

            try {
                con.doAction(actions[random],isSlow);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
