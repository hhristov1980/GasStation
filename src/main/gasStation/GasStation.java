package main.gasStation;

import main.cars.Car;
import main.fuelLoaderDevice.FuelDispenser;
import main.payDesk.PayDesk;
import main.persons.Cashier;
import main.persons.Loader;
import main.util.Randomizer;

import java.time.LocalDateTime;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;

public class GasStation {
    private String name;
    private CopyOnWriteArrayList<FuelDispenser> fuelDispensers;
    private CopyOnWriteArrayList<PayDesk> payDesks;
    private BlockingQueue<Cashier> cashiers;
    private BlockingQueue<Loader> loaders;
    private Statistics statistics;
    private double turnover;

    public GasStation(String name){
        if(name.length()>0){
            this.name = name;
        }
        fuelDispensers = new CopyOnWriteArrayList<>();
        payDesks = new CopyOnWriteArrayList<>();
        cashiers = new LinkedBlockingQueue<>(2);
        loaders = new LinkedBlockingQueue<>(2);
    }

    public void setStatistics(Statistics statistics) {
        this.statistics = statistics;
    }

    public void addLoader(Loader l){
        try {
            loaders.put(l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void addCashiers(Cashier c){
        try {
            cashiers.put(c);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void addFuelDispenser(FuelDispenser fd){
        if(fuelDispensers.size()<5){
            fuelDispensers.add(fd);
        }
        else {
            System.out.println("No more free space for fuel dispensers");
        }

    }
    public void addPayDesks(PayDesk pd){
        if(payDesks.size()<2){
            payDesks.add(pd);
        }
        else {
            System.out.println("No more free space for pay desks");
        }
    }

    public synchronized void goToFuelDispenser(Car car){
        int dispenser = Randomizer.getRandomInt(0,fuelDispensers.size()-1);
        car.setFuelDispenser(fuelDispensers.get(dispenser));
        try {
            fuelDispensers.get(dispenser).getCarQueue().put(car);
            notifyAll();
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void goToPay(Car car){
        int payDesk = Randomizer.getRandomInt(0,payDesks.size()-1);
        try {
            payDesks.get(payDesk).getCarQueue().put(car);
            notifyAll();
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void refuelCar(){
        boolean carWaiting = false;
        for(FuelDispenser f: fuelDispensers){
            if(!f.getCarQueue().isEmpty()){
                Car car = f.getCarQueue().peek();
                if(!car.isRefueled()){
                    int litters = Randomizer.getRandomInt(10,40);
                    car.addFuel(litters);
                    System.out.println("Car "+car.getCarId()+" was successfully refueled with "+litters+" litters "+car.getFuelType());
                    carWaiting = true;
                    notifyAll();

                }
            }
        }
        if(!carWaiting){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void takeMoney(){
        boolean carWaiting = false;
        for(PayDesk p: payDesks){
            if(!p.getCarQueue().isEmpty()){
                try {
                    Car car = p.getCarQueue().take();
                    turnover+=car.getBill();
                    System.out.println("Car "+car.getCarId()+" paid "+ car.getBill()+ " for fuel");
                    for(FuelDispenser fd: fuelDispensers){
                        if(fd.getDeviceId().equals(car.getFuelDispenser().getDeviceId())){
                            fd.getCarQueue().take();
                        }
                    }
                    if(!statistics.getGeneralStatistics().containsKey(car.getFuelDispenser().getDeviceId())){
                        statistics.getGeneralStatistics().put(car.getFuelDispenser().getDeviceId(),new ConcurrentHashMap<>());
                    }
                    if(!statistics.getGeneralStatistics().get(car.getFuelDispenser().getDeviceId()).containsKey(car.getFuelType())){
                        statistics.getGeneralStatistics().get(car.getFuelDispenser().getDeviceId()).put(car.getFuelType(),new CopyOnWriteArrayList<>());
                    }
                    statistics.getGeneralStatistics().get(car.getFuelDispenser().getDeviceId()).get(car.getFuelType()).add((ConcurrentHashMap<Integer, LocalDateTime>) new ConcurrentHashMap<>().put(car.getLittersToPutInTank(),LocalDateTime.now()));
                    notifyAll();
                    carWaiting = true;
                    car.setPaid(true);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
        if(!carWaiting){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void open(){
        for (Loader l: loaders){
            l.start();
        }
        for(Cashier c: cashiers){
            c.start();
        }
    }


    public String getName() {
        return name;
    }
}
