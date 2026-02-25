package petstore;

import jakarta.persistence.*;

import java.util.List;

@Table(name = "petstore")
@Entity
public class PetStore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, length = 10)
    private Long id;
    @Column(name = "NAME", nullable = false, length = 50)
    private String name;
    @Column(name = "MANAGER_NAME", nullable = false, length = 50)
    private String managerName;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @OneToMany(mappedBy = "petStore", cascade = CascadeType.ALL)
    private List<Product> products;

    @OneToMany(mappedBy = "petStore", cascade = CascadeType.ALL)
    private List<Animal> animals;

    public PetStore(String label, String managerName) {
        this.name = label;
        this.managerName = managerName;
    }

    public PetStore() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }


    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

}
