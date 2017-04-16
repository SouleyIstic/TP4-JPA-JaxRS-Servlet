package fr.tp4sir.tp4sir;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class ElectronicDevice extends SmartDevice {

    private double consomationWatH;
    private Person person;


    public ElectronicDevice() {

    }

    public ElectronicDevice(String name, double consomationWatH) {
        super(name);
        this.consomationWatH = consomationWatH;
    }

    public double getconsomationWatH() {
        return consomationWatH;
    }

    public void setconsomationWatH(double consomationWatH) {
        this.consomationWatH = consomationWatH;
    }

    @ManyToOne
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}


