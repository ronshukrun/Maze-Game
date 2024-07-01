package algorithms.search;

<<<<<<< HEAD

=======
>>>>>>> f3c6356ab4a43a594505d79174002b0a867042a0
/**
 * This abstract class describes a state in any searchable problem.
 */
public abstract class AState {

    protected Object state;
    protected AState prevState;
    protected double cost;

<<<<<<< HEAD

    /**
     * Determines whether the state is valid under the conditions of the problem.
     * Each subclass will implement this method based on its own criteria.
     * @return true if the state is valid, false otherwise
     */
    public abstract boolean validState();

    /**
     * Compares this state with another state for equality.
     * Each subclass will implement this method based on its own criteria.
=======
    /**
     * Determines whether the state is valid under the conditions of the problem.
     * @return true if the state is valid, false otherwise
     */
    public abstract boolean validState();
    /**
     * Compares this state with another state for equality.
>>>>>>> f3c6356ab4a43a594505d79174002b0a867042a0
     * @param state the state to compare with
     * @return true if the states are equal, false otherwise
     */
    public abstract boolean equalsState(AState state);

<<<<<<< HEAD
    /**
     * Gets the state object.
     * Each subclass will define the state object differently.
     * @return the state object
     */
    public Object getState() {return this.state;}

=======
    public double getCost() {return this.cost;}

    public void setCost(double cost) {this.cost = cost;}

    public Object getState() {return this.state;}
>>>>>>> f3c6356ab4a43a594505d79174002b0a867042a0
    /**
     * Gets the previous state from which the current state was reached
     * during the execution of a particular search algorithm.
     * @return the previous state
     */
    public AState getPrevState() {return prevState;}
    /**
     * Sets the previous state.
     * When discovering a new state, this method updates its prevState field to the state
     * from which it was discovered.
     * @param prevState the previous state
     */
    public void setPrevState(AState prevState) {
        if (prevState == null) {
            throw new IllegalArgumentException("The provided previous state is null.");
        }
        this.prevState = prevState;
    }

<<<<<<< HEAD
    /**
     * Gets the cost of reaching this state.
     * @return the cost of the state
     */
    public double getCost() {
        return this.cost;
    }
    /**
     * Sets the cost of reaching this state.
     * @param cost the cost to set
     */
    public void setCost(double cost) {
        this.cost = cost;
    }
=======

>>>>>>> f3c6356ab4a43a594505d79174002b0a867042a0
}