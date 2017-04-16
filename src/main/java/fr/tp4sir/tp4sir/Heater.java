package fr.tp4sir.tp4sir;

import javax.persistence.*;


@Entity
public class Heater extends SmartDevice {
	private String puissance;
    private Home home;

    public Heater() {
    }

    public Heater(String name) {
        super(name);
    }


    @ManyToOne
    public Home getHome() {
        return home;
    }

    public void setHome(Home home) {
        this.home = home;
    }

	public String getPuissance() {
		return puissance;
	}

	public void setPuissance(String puissance) {
		this.puissance = puissance;
	}
    
    
}
