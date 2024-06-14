package algorithms.search;


public abstract class AState {

    protected Object state;
    protected AState prevState;
    protected double cost;

    public abstract boolean validState();

    public abstract boolean equalsState(AState state);

    public double getCost() {return this.cost;}

    public void setCost(double cost) {this.cost = cost;}

    public Object getState() {return this.state;}

    public AState getPrevState() {return prevState;}

    public void setPrevState(AState prevState) {
        if (prevState == null) {
            throw new IllegalArgumentException("The provided previous state is null.");
        }
        this.prevState = prevState;
    }


}