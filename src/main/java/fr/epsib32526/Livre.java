package fr.epsib32526;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "livre")
public class Livre implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false, length = 10)
    private Integer id;
    @Column(name = "TITRE", nullable = false, length = 255)
    private String titre;
    @Column(name = "AUTEUR", nullable = false, length = 50)
    private String auteur;

    @ManyToMany
    @JoinTable(name="COMPO",
            joinColumns= @JoinColumn(name="ID_LIV", referencedColumnName= "ID"),
            inverseJoinColumns= @JoinColumn(name="ID_EMP", referencedColumnName= "ID")
    )
    private Set<Emprunt> emprunts = new HashSet<>();


    public Livre(String titre, String auteur) {
        this.titre = titre;
        this.auteur = auteur;
    }

    public Livre() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public Set<Emprunt> getEmprunts() {
        return emprunts;
    }



}
