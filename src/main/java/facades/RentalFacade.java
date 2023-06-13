package facades;

import dtos.HouseDTO;
import dtos.RentalDTO;
import dtos.UserDTO;
import entities.House;
import entities.Rental;
import entities.Role;
import entities.User;
import security.errorhandling.AuthenticationException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author lam@cphbusiness.dk
 */
public class RentalFacade {

    private static EntityManagerFactory emf;
    private static RentalFacade instance;

    private RentalFacade() {
    }

    /**
     * @param _emf
     * @return the instance of this facade.
     */
    public static RentalFacade getRentalFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new RentalFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }


    public List<RentalDTO> getAllRentals() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Rental> query = em.createQuery("SELECT r FROM Rental r", Rental.class);
        List<Rental> rentals = query.getResultList();
        System.out.println(rentals);
        for (Rental rental : rentals) {
            System.out.println(rental.getUsers());
        }
        return RentalDTO.getDtos(rentals);
    }


    public RentalDTO createRental(HouseDTO houseDTO, UserDTO userDTO, RentalDTO rentalDTO) {
        List<Role> roleList = new ArrayList<>();
        roleList.add(new Role("user"));

        Set<User> users = new HashSet<>();
        User user = new User(userDTO.getUserName(), userDTO.getUserPass(), roleList, userDTO.getName(), userDTO.getPhone(), userDTO.getJob());
        users.add(user);
        House house = new House(houseDTO.getAddress(), houseDTO.getCity(), houseDTO.getNumerOfRooms());
        EntityManager em = getEntityManager();
        Rental rental = new Rental(rentalDTO.getStartDate(), rentalDTO.getEndDate(), rentalDTO.getPriceAnnual(), rentalDTO.getDeposit(), rentalDTO.getContactPerson(), house, users);
        try {
            em.getTransaction().begin();
            em.persist(user);
            em.persist(house);
            em.persist(rental);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new RentalDTO(rental);
    }

    public RentalDTO addTenant(String newTenant, Long id) {

        EntityManager em = getEntityManager();
        User newUser = em.find(User.class, newTenant);
        Rental rental = em.find(Rental.class, id);

        rental.getUsers().add(newUser);

        try {
            em.getTransaction().begin();
            em.merge(rental);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new RentalDTO(rental);
    }


    public RentalDTO removeTenant(String oldTenant, Long id) {

        EntityManager em = getEntityManager();
        User oldUser = em.find(User.class, oldTenant);
        Rental rental = em.find(Rental.class, id);

        for(User user : rental.getUsers()){
            if(user.getUserName().equals(oldUser.getUserName())){
                rental.getUsers().remove(user);
            }
        }
        try{
            em.getTransaction().begin();
            em.merge(rental);
            em.getTransaction().commit();
        }finally{
            em.close();
        }
        return new RentalDTO(rental);
    }
}
