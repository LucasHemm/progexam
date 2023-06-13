package dtos;

import entities.House;
import entities.Rental;

import java.util.List;
import java.util.Set;

public class HouseDTO {


    private String address;
    private String city;
    private int numerOfRooms;

    public HouseDTO(String address, String city, int numerOfRooms) {
        this.address = address;
        this.city = city;
        this.numerOfRooms = numerOfRooms;

    }
    public HouseDTO(House house) {
        this.address = house.getAddress();
        this.city = house.getCity();
        this.numerOfRooms = house.getNumberOfRooms();
    }

    public HouseDTO(){
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

    @Override
    public String toString() {
        return "HouseDTO{" +
                "address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", numerOfRooms=" + numerOfRooms +
                '}';
    }
}
