package fr.tp4sir.tp4sir;

import javax.persistence.*;

import java.util.Collection;


@Entity
public class Home {

    private long id;
    private String nameHome;
    private double tailleMaison;
    private int nbrePiece;
    private Person person;
    
    //Les entités doivent de préférence ne contenir que les @ JPA, setteurs, getteurs ce qui correspond au code métier (les tables dans la BD en gros).
    //Les initialisations devront se faire de préférence dans une autre classe qui contient pas le @Entity comme les classes de test, ton main, etc..
    private Collection<Heater> heaters;

    public Home(String nameHome, Collection<Heater> heaters) {
        this.nameHome = nameHome;
        this.heaters = heaters;
        initHeaters();
    }

    private void initHeaters() {
        if (heaters != null)
            for (Heater heater : heaters) {
                heater.setHome(this);
            }
    }

    public Home() {
    }

    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getnameHome() {
        return nameHome;
    }

    public void setnameHome(String nameHome) {
        this.nameHome = nameHome;
    }
    
    

    public double getTailleMaison() {
		return tailleMaison;
	}

	public void setTailleMaison(double tailleMaison) {
		this.tailleMaison = tailleMaison;
	}

	public int getNbrePiece() {
		return nbrePiece;
	}

	public void setNbrePiece(int nbrePiece) {
		this.nbrePiece = nbrePiece;
	}

	@OneToMany(mappedBy = "home", cascade = CascadeType.PERSIST)
    public Collection<Heater> getHeaters() {
        return heaters;
    }

    public void setHeaters(Collection<Heater> heaters) {
        this.heaters = heaters;
    }

    @ManyToOne
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
