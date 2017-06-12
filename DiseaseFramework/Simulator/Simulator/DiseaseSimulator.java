package Simulator;

class DiseaseSimulator extends Simulator {
    public static void main(String[] args) {
        new DiseaseSimulator().start();
    }
    void start() {
        events = new ListQueue();
        
        Person.meanInfectiousDuration = 5.0;
        Person.meanInterContactInterval = 4.0;
        Person person;
        for (int i = 0; i< 995; i++) {
            new Person(Person.SUSCEPTIBLE);
        }
        for (int i = 0; i< 5; i++) {
            person = new Person(Person.INFECTIOUS);
            person.recoveryTime = Random.exponential(Person.meanInfectiousDuration);
            person.scheduleNextEvent(this);
        }
        
        doAllEvents();
    }
}