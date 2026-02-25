package petstore;

import jakarta.persistence.*;

@Table(name = "product")
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, length = 10)
    private Long id;
    @Column(name= "CODE", nullable = false, length = 50)
    private String code;
    @Column(name = "LABEL", nullable = false, length = 50)
    private String label;
    @Enumerated(EnumType.STRING)
    private Animal.ProdType type;
    @Column(name = "PRICE", nullable = false, length = 50)
    private Double price;

    @ManyToOne
    @JoinColumn(name = "petstore_id")
    private PetStore petStore;

    public Product(String code, String label, Double price) {
        this.code = code;
        this.label = label;
        this.price = price;
    }

    public  Product() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Animal.ProdType getType() {
        return type;
    }

    public void setType(Animal.ProdType type) {
        this.type = type;
    }

    public PetStore getPetStore() {
        return petStore;
    }

    public void setPetStore(PetStore petStore) {
        this.petStore = petStore;
    }
}
