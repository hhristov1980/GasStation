package main.persons;

import main.gasStation.GasStation;

public abstract class Person extends Thread{
    private static int uniqueId = 1;
    private String name;
    private int personId;
    protected GasStation gasStation;

    public Person(String name, GasStation gasStation){
        if(name.length()>0){
            this.name = name;
        }
        this.gasStation = gasStation;
        personId = uniqueId++;
    }

    @Override
    public abstract void run();
}
