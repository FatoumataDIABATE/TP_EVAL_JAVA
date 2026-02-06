package fr.epsib32526;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Table(name = "client")
@Entity
public class Client implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false, length = 10)
    private Integer id;
    @Column(name = "NOM", nullable = false, length = 50)
    private String nom;
    @Column(name = "PRENOM", nullable = false, length = 50)
    private String prenom;

    @OneToMany(mappedBy="client")
    private Set<Emprunt> emprunts = new HashSet<>();

    public Client(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }
    public Client() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Set<Emprunt> getEmprunts() {
        return emprunts;
    }
}
