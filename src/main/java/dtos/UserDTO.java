package dtos;

import entities.Rental;
import entities.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UserDTO {

    private String userName;
    private String userPass;
    private List<RoleDTO> roleList = new ArrayList<>();
    private String name;
    private String phone;
    private String job;
    private Set<RentalDTO> rentalDTOs = new HashSet<>();

    public UserDTO(User user) {
        this.userName = user.getUserName();
        this.userPass = user.getUserPass();
        this.roleList = user.getRoleList().stream().map(r -> new RoleDTO(r)).collect(Collectors.toList());
        this.name = user.getName();
        this.phone = user.getPhone();
        this.job = user.getJob();
        for(Rental rental: user.getRentals()){
            RentalDTO rentalDTO = new RentalDTO(rental);
            System.out.println("HEEEEEEEEEJ*****"+rentalDTO);
            this.rentalDTOs.add(rentalDTO);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public static List<UserDTO> getDtos(List<User> persons) {
        return persons.stream().map(p -> new UserDTO(p)).collect(Collectors.toList());
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public List<RoleDTO> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<RoleDTO> roleList) {
        this.roleList = roleList;
    }

    public Set<RentalDTO> getRentalDTOs() {
        return rentalDTOs;
    }

    public void setRentalDTOs(Set<RentalDTO> rentalDTOs) {
        this.rentalDTOs = rentalDTOs;
    }
}
