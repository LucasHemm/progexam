package dtos;

import java.util.Set;

public class RentalDTO {

    private String startDate;
    private String endDate;
    private int priceAnnual;
    private int deposit;
    private String contactPerson;
    private HouseDTO houseDTO;
    private Set<UserDTO> userDTOs;

    public RentalDTO() {
    }

    public RentalDTO(String startDate, String endDate, int priceAnnual, int deposit, String contactPerson, HouseDTO houseDTO, Set<UserDTO> userDTOs) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.priceAnnual = priceAnnual;
        this.deposit = deposit;
        this.contactPerson = contactPerson;
        this.houseDTO = houseDTO;
        this.userDTOs = userDTOs;
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

    public Set<UserDTO> getUserDTOs() {
        return userDTOs;
    }

    public void setUserDTOs(Set<UserDTO> userDTOs) {
        this.userDTOs = userDTOs;
    }
}