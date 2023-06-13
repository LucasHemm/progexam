package entities;

import org.mindrot.jbcrypt.BCrypt;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.*;

@Entity
@NamedQuery(name = "User.deleteAllRows", query = "DELETE from User ")
@Table(name = "users")
public class User implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @NotNull
  @Column(name = "user_name", length = 25)
  private String userName;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 255)
  @Column(name = "user_pass")
  private String userPass;
  @JoinTable(name = "user_roles", joinColumns = {
    @JoinColumn(name = "user_name", referencedColumnName = "user_name")}, inverseJoinColumns = {
    @JoinColumn(name = "role_name", referencedColumnName = "role_name")})
  @ManyToMany
  private List<Role> roleList = new ArrayList<>();

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "phone", nullable = false)
  private String phone;

  @Column(name = "job", nullable = false)
  private String job;

  @ManyToMany(mappedBy = "users",fetch = FetchType.EAGER)
  private Set<Rental> rentals = new LinkedHashSet<>();

  public Set<Rental> getRentals() {
    return rentals;
  }

  public void setRentals(Set<Rental> rentals) {
    this.rentals = rentals;
  }

  public String getJob() {
    return job;
  }

  public void setJob(String job) {
    this.job = job;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<String> getRolesAsStrings() {
    if (roleList.isEmpty()) {
      return null;
    }
    List<String> rolesAsStrings = new ArrayList<>();
    roleList.forEach((role) -> {
        rolesAsStrings.add(role.getRoleName());
      });
    return rolesAsStrings;
  }

  public User() {}

  //TODO Change when password is hashed
   public boolean verifyPassword(String pw){
    return BCrypt.checkpw(pw, userPass);
    }

  public User(String userName, String userPass) {
    this.userName = userName;
    this.userPass = BCrypt.hashpw(userPass, BCrypt.gensalt());
  }
  public User(String userName, String userPass, List<Role> roleList) {
    this.userName = userName;
    this.userPass = BCrypt.hashpw(userPass, BCrypt.gensalt());
    this.roleList = roleList;
  }
  public User(String userName, String userPass, String name, String phone, String job) {
    this.userName = userName;
    this.userPass = BCrypt.hashpw(userPass, BCrypt.gensalt());
    this.name = name;
    this.phone = phone;
    this.job = job;
  }

  public User(String userName, String userPass,List<Role> roleList, String name, String phone, String job) {
    this.userName = userName;
    this.userPass = BCrypt.hashpw(userPass, BCrypt.gensalt());
    this.roleList = roleList;
    this.name = name;
    this.phone = phone;
    this.job = job;
  }

  public User(String userName, String userPass,List<Role> roleList, String name, String phone, String job, Set<Rental> rentals) {
    this.userName = userName;
    this.userPass = BCrypt.hashpw(userPass, BCrypt.gensalt());
    this.roleList = roleList;
    this.name = name;
    this.phone = phone;
    this.job = job;
    this.rentals = rentals;
  }


  @Override
  public String toString() {
    return "User{" +
            "userName='" + userName + '\'' +
            ", userPass='" + userPass + '\'' +
            ", roleList=" + roleList +
            ", name='" + name + '\'' +
            ", phone='" + phone + '\'' +
            ", job='" + job + '\'' +
            ", rentals=" + rentals +
            '}';
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getUserPass() {
    return this.userPass;
  }

  public void setUserPass(String userPass) {
    this.userPass = BCrypt.hashpw(userPass, BCrypt.gensalt());;
  }

  public List<Role> getRoleList() {
    return roleList;
  }

  public void setRoleList(List<Role> roleList) {
    this.roleList = roleList;
  }

  public void addRole(Role userRole) {
    roleList.add(userRole);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    User user = (User) o;
    return userName != null && Objects.equals(userName, user.userName);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
