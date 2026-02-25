package petstore;

import jakarta.persistence.*;

import java.util.Date;

@Table(name = "animal")
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, length = 10)
    private Long id;
    @Column(name = "BIRTH", nullable = false)
    private Date birth;
    @Column(name = "COULEUR", nullable = false, length = 50)
    private String couleur;

    @ManyToOne
    @JoinColumn(name = "petstore_id")
    private PetStore petStore;

    public Animal(Date birth, String couleur) {
        this.birth = birth;
        this.couleur = couleur;
    }

    public enum FishLivEnv {
        FRESH_WATER,
        SEA_WATER
    }

    public enum ProdType {
        FOOD,
        ACCESSORY,
        CLEANING
    }

     public Animal() {

     }

     public Long getId() {
         return id;
     }

     public void setId(Long id) {
         this.id = id;
     }

     public Date getBirth() {
         return birth;
     }

     public void setBirth(Date birth) {
         this.birth = birth;
     }

     public String getCouleur() {
         return couleur;
     }

     public void setCouleur(String couleur) {
         this.couleur = couleur;
     }

    public PetStore getPetStore() {
        return petStore;
    }

    public void setPetStore(PetStore petStore) {
        this.petStore = petStore;
    }
}
