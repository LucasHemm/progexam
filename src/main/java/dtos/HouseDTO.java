package dtos;

import entities.House;
import entities.Rental;

import java.util.List;
import java.util.Set;

public class HouseDTO {


    private String address;
    private String city;
    private int numerOfRooms;
    private List<RentalDTO> rentalDTOs;

    public HouseDTO(String address, String city, int numerOfRooms, List<RentalDTO> rentalDTOs) {
        this.address = address;
        this.city = city;
        this.numerOfRooms = numerOfRooms;
        this.rentalDTOs = rentalDTOs;
    }
    public HouseDTO(House house) {
        this.address = house.getAddress();
        this.city = house.getCity();
        this.numerOfRooms = house.getNumberOfRooms();
        List<Rental> rentalList = (List<Rental>) house.getRentals();
        this.rentalDTOs = RentalDTO.getDtos(rentalList);
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

    public List<RentalDTO> getRentalDTOs() {
        return rentalDTOs;
    }

    public void setRentalDTOs(List<RentalDTO> rentalDTOs) {
        this.rentalDTOs = rentalDTOs;
    }
}
