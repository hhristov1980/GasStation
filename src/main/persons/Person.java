package main.persons;

public abstract class Person extends Thread{
    private static int uniqueId = 1;
    private String name;
    private int personId;

    public Person(String name){
        if(name.length()>0){
            this.name = name;
        }
        personId = uniqueId++;
    }

    @Override
    public abstract void run();
}
