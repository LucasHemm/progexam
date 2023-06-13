package dtos;

import entities.House;
import entities.Rental;
import entities.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RentalDTO {

    private String startDate;
    private String endDate;
    private int priceAnnual;
    private int deposit;
    private String contactPerson;
    private HouseDTO houseDTO;
    private Set<String> userDTOs = new HashSet<>();
    private Long id;

    public RentalDTO() {
    }

    public RentalDTO(String startDate, String endDate, int priceAnnual, int deposit, String contactPerson, HouseDTO houseDTO, Set<UserDTO> userDTOs,Long id) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.priceAnnual = priceAnnual;
        this.deposit = deposit;
        this.contactPerson = contactPerson;
        this.houseDTO = houseDTO;
        for(UserDTO userDTO : userDTOs){
            this.userDTOs.add(userDTO.getUserName());
        }
        this.id = id;
    }
    public RentalDTO(Rental rental) {
        this.startDate = rental.getStartDate();
        this.endDate = rental.getEndDate();
        this.priceAnnual = rental.getPriceAnnual();
        this.deposit = rental.getDeposit();
        this.contactPerson = rental.getContactPerson();
        this.houseDTO = new HouseDTO(rental.getHouse());
        for(User user : rental.getUsers()){
            this.userDTOs.add(user.getUserName());
        }
    }

    //    public static List<RentalDTO> getDtos(List<Rental> rentals) {
//        return rentals.stream().map(r -> new RentalDTO(r.getStartDate(),r.getEndDate()r.getPriceAnnual()r.getDeposit()r.getContactPerson(),new HouseDTO(r.getHouse(),r.get)).collect(Collectors.toList());
//    }
    public static List<RentalDTO> getDtos(List<Rental> rentals) {
        List<RentalDTO> rentalDTOs = new ArrayList<>();
        for (Rental rental: rentals) {
            HouseDTO houseDTO = new HouseDTO(rental.getHouse());
            Set<UserDTO> userDTOs = rental.getUsers().stream().map(u -> new UserDTO(u)).collect(Collectors.toSet());
            RentalDTO rentalDTO = new RentalDTO(rental.getStartDate(),rental.getEndDate(),rental.getPriceAnnual(),rental.getDeposit(),rental.getContactPerson(),houseDTO,userDTOs,rental.getId());
            rentalDTOs.add(rentalDTO);
        }
        return rentalDTOs;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getPriceAnnual() {
        return priceAnnual;
    }

    public void setPriceAnnual(Integer priceAnnual) {
        this.priceAnnual = priceAnnual;
    }

    public Integer getDeposit() {
        return deposit;
    }

    public void setDeposit(Integer deposit) {
        this.deposit = deposit;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public HouseDTO getHouseDTO() {
        return houseDTO;
    }

    public void setHouseDTO(HouseDTO houseDTO) {
        this.houseDTO = houseDTO;
    }

    public void setPriceAnnual(int priceAnnual) {
        this.priceAnnual = priceAnnual;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }

    public Set<String> getUserDTOs() {
        return userDTOs;
    }

    public void setUserDTOs(Set<String> userDTOs) {
        this.userDTOs = userDTOs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "RentalDTO{" +
                "startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", priceAnnual=" + priceAnnual +
                ", deposit=" + deposit +
                ", contactPerson='" + contactPerson + '\'' +
                ", houseDTO=" + houseDTO +
                ", userDTOs=" + userDTOs +
                '}';
    }
}
