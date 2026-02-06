package fr.epsib32526;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Table(name = "emprunt")
@Entity
public class Emprunt implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false, length = 10)
    private Integer id;
    @Column(name = "DATE_DEBUT", nullable = false)
    private LocalDateTime dateDebut;
    @Column(name = "DATE_FIN", nullable = false)
    private LocalDateTime dateFin;
    @Column(name = "DELAI", nullable = false)
    private Integer delai;


    @ManyToMany
    @JoinTable(name="COMPO",
            joinColumns= @JoinColumn(name="ID_EMP", referencedColumnName= "ID"),
            inverseJoinColumns= @JoinColumn(name="ID_LIV", referencedColumnName= "ID")
    )

    private Set<Livre> livres = new HashSet<>();

    @ManyToOne
    @JoinColumn(name="ID_CLIENT")
    private Client client;

    public Emprunt(LocalDateTime date_debut, LocalDateTime date_fin, Integer delai) {
        this.dateDebut = date_debut;
        this.dateFin = date_fin;
        this.delai = delai;
    }

    public Emprunt() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDateTime dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDateTime getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDateTime dateFin) {
        this.dateFin = dateFin;
    }

    public Integer getDelai() {
        return delai;
    }

    public void setDelai(Integer delai) {
        this.delai = delai;
    }


    public Set<Livre> getLivres() {
        return livres;
    }
}
