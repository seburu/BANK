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

        for (int i = 0; i < actions.length; i++) {
            con.doAction(actions[i]);
        }
    }

}
