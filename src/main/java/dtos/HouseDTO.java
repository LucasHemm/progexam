package dtos;

import java.util.Set;

public class HouseDTO {


    private String address;
    private String city;
    private int numerOfRooms;
    private Set<RentalDTO> rentalDTOs;

    public HouseDTO(String address, String city, int numerOfRooms, Set<RentalDTO> rentalDTOs) {
        this.address = address;
        this.city = city;
        this.numerOfRooms = numerOfRooms;
        this.rentalDTOs = rentalDTOs;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getNumerOfRooms() {
        return numerOfRooms;
    }

    public void setNumerOfRooms(int numerOfRooms) {
        this.numerOfRooms = numerOfRooms;
    }

    public Set<RentalDTO> getRentalDTOs() {
        return rentalDTOs;
    }

    public void setRentalDTOs(Set<RentalDTO> rentalDTOs) {
        this.rentalDTOs = rentalDTOs;
    }
}
