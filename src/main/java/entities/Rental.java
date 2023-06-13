package entities;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "rental")
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "start_date", nullable = false)
    private String startDate;

    @Column(name = "end_date", nullable = false)
    private String endDate;

    @Column(name = "price_annual", nullable = false)
    private Integer priceAnnual;

    @Column(name = "deposit", nullable = false)
    private Integer deposit;

    @Column(name = "contact_person", nullable = false)
    private String contactPerson;

    @ManyToMany
    @JoinTable(name = "rental_users",
            joinColumns = @JoinColumn(name = "rental_id"),
            inverseJoinColumns = @JoinColumn(name = "users_user_name"))
    private Set<User> users = new LinkedHashSet<>();

    @ManyToOne
    @JoinColumn(name = "house_id")
    private House house;


    public Rental() {
    }
    public Rental(String startDate, String endDate, Integer priceAnnual, Integer deposit, String contactPerson, House house, Set<User> users) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.priceAnnual = priceAnnual;
        this.deposit = deposit;
        this.contactPerson = contactPerson;
        this.house = house;
        this.users = users;
    }


    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public Integer getDeposit() {
        return deposit;
    }

    public void setDeposit(Integer deposit) {
        this.deposit = deposit;
    }

    public Integer getPriceAnnual() {
        return priceAnnual;
    }

    public void setPriceAnnual(Integer priceAnnual) {
        this.priceAnnual = priceAnnual;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}