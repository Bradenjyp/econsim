package Simulator;

class Person extends Event {
    final static int SUSCEPTIBLE = 0;
    final static int INFECTIOUS = 1;
    final static int RECOVERED = 2;
    final static int nStates = 3;
    static double meanInfectiousDuration;
    static double meanInterContactInterval;

    /**
     *  A vector with every person in the population
    */
    static java.util.Vector population;
    /**
     * A count of the number of people in each state
     */
    static int count[];  
    static {
        population = new java.util.Vector();
        count = new int[nStates];
        for (int i=0; i< nStates; i++) {
          count[i] = 0;
        }
    }
    
    int state;
    int nextState;
    double recoveryTime;
    
    Person (int initialState) {
        state = initialState;
        population.addElement(this);
        count[state]++;
    }
    void execute(AbstractSimulator simulator) {
        if (nextState == INFECTIOUS) { // action is make contact
            makeContact((Simulator) simulator);
            scheduleNextEvent((Simulator) simulator);
        } // else action is recover
        count[state]--;
        state = nextState;
        count[state]++;
        display((Simulator) simulator);
    }
    
    /**
    * Display the current population distribution
    */
    void display(Simulator simulator) {
        System.out.print("t= " + simulator.now());
        for (int i=0; i< nStates; i++) {
			if (i == 0) System.out.print(" Susceptible = " + count[i]);
			if (i == 1) System.out.print(" Contagious = " + count[i]);
			if (i == 2) System.out.print(" Recovered = " + count[i]);
          
        }
        System.out.println("");
    }
    
    /**
    * Pick a random member of the population and infect the person if it
    * is susceptible.
    */
    void makeContact(Simulator simulator) {
        Person person = (Person) population.elementAt(
          (int) (population.size() * Math.random()));
        if (person.state != SUSCEPTIBLE) return;
        person.recoveryTime = simulator.now() + Random.exponential(meanInfectiousDuration);
        person.scheduleNextEvent(simulator);
    }
    /**
    * Schedule the next event, which will be a contact or recovery, whichever
    * comes first.
    */
    void scheduleNextEvent(Simulator simulator) {
        double nextContactTime = simulator.now() + Random.exponential(meanInterContactInterval);
        if (nextContactTime < recoveryTime) {
            nextState = INFECTIOUS;
            time = nextContactTime;
        } else {
            nextState = RECOVERED;
            time = recoveryTime;
        }
        simulator.insert(this);
    }
}