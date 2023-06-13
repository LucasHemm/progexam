package facades;


import dtos.HouseDTO;
import dtos.RentalDTO;
import dtos.UserDTO;
import entities.House;
import entities.Rental;
import entities.Role;
import entities.User;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserFacadeTest {

    private static EntityManagerFactory emf;
    private static UserFacade facade;
    private static User u1, u2;
    private static Rental r1, r2, r3;
    private static House h1, h2, h3;
    List<Role> userList = new ArrayList<>();

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = UserFacade.getUserFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        List<Role> adminList = new ArrayList<>();
        Role uRole = new Role("user");
        Role aRole = new Role("admin");
        userList.add(uRole);
        adminList.add(aRole);

        h1 = new House("Hansensvej 12", "København", 5);
        h2 = new House("lyngbyhovedgade", "Lyngby", 3);
        h3 = new House("roskildevej", "roskilde", 7);

        r1 = new Rental("15 maj", "20 dec", 100000, 15000, "hansi", h1);
        r2 = new Rental("13 jan", "9 nov", 90000, 12500, "Peterski", h2);
        r3 = new Rental("5 feb", "22 oct", 80000, 5600, "nik", h3);
        Set<Rental> rentals1 = new HashSet<>();
        rentals1.add(r1);
        Set<Rental> rentals2 = new HashSet<>();
        rentals2.add(r2);


        u1 = new User("user", "123", userList, "peter", "12345678", "kaptajn", rentals1);
        u2 = new User("admin", "123", adminList, "hans", "12345678", "maler", rentals2);
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Rental.deleteAll").executeUpdate();
            em.createNamedQuery("User.deleteAllRows").executeUpdate();
            em.createNamedQuery("House.deleteAll").executeUpdate();
            em.createQuery("delete from Role").executeUpdate();
            em.persist(uRole);
            em.persist(aRole);
            em.persist(h1);
            em.persist(h2);
            em.persist(h3);
            em.persist(u1);
            em.persist(u2);
            em.persist(r1);
            em.persist(r2);
            em.persist(r3);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }


    //this tests the getAll method
    @Test
    public void getAllUsers() {
        List<UserDTO> users = facade.getAll();
        Assertions.assertEquals(2, users.size());
    }


    //this tests the getUser method
    @Test
    public void getUser() {
        UserDTO user = facade.getUser(u1.getUserName());
        Assertions.assertEquals(u1.getUserName(), user.getUserName());
    }



}
