package Simulator;

abstract class  AbstractEvent implements Comparable {
    abstract void execute(AbstractSimulator simulator);
}