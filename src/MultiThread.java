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

        //Loop that does 20 different actions (for each thread)
        for (int i = 0; i < 20; i++) {
            int random = (int)(Math.random()*actions.length);
            //gives random action an random amount value.
            actions[random].amount = (int) (Math.random() * 490)+10;

            try {
                //Ececute specific action
                con.doAction(actions[random],isSlow);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
