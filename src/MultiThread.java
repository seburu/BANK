
public class MultiThread extends Thread {

    Action[] actions;
    Controller con;


    public MultiThread (Action[] actions, Controller con){ // Constructor
        this.actions = actions;
        this.con = con;
    }


    @Override
    public void run(){
        //doAction i forloop

        for (int i = 0; i < 20; i++) {
            int random = (int)(Math.random()*actions.length);
            actions[random].amount = (int) (Math.random() * 490)+10;

            con.doAction(actions[random]);
        }
    }

}
