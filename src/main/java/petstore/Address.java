package petstore;

import jakarta.persistence.*;

@Table(name = "address")
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, length = 10)
    private Long id;
    @Column(name = "NUMBER", nullable = false, length = 10)
    private String number;
    @Column(name = "STREET", nullable = false, length = 255)
    private String street;
    @Column(name = "CITY", nullable = false, length = 255)
    private String city;
    @Column(name = "ZIP_CODE", nullable = false, length = 10)
    private String zipCode;

    public Address(String number, String street, String city, String zipCode) {
        this.number = number;
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
    }

    public Address() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
