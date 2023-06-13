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

public class RentalFacadeTest {

    private static EntityManagerFactory emf;
    private static RentalFacade facade;
    private static User u1, u2;
    private static Rental r1, r2, r3;
    private static House h1, h2, h3;
    List<Role> userList = new ArrayList<>();

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = RentalFacade.getRentalFacade(emf);
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


    //this tests the getAllRentals method
    @Test
    public void testGetAllRentals() {
        List<RentalDTO> rentalDTOs = facade.getAllRentals();
        Assertions.assertEquals(3, rentalDTOs.size());
    }

    //this tests the deleteRental method
    @Test
    public void testDeleteRental() {
        facade.deleteRental(r1.getId());
        List<RentalDTO> rentalDTOs = facade.getAllRentals();
        Assertions.assertEquals(2, rentalDTOs.size());
    }

    //this tests the addTenant method
    @Test
    public void testAddTenant() {
        RentalDTO rentalDTO = facade.addTenant(u2.getUserName(), r3.getId());
        System.out.println(rentalDTO);
        Assertions.assertEquals(1, rentalDTO.getUserDTOs().size());
    }

    //this test the removeTenant method
    @Test
    public void testRemoveTenant() {
        RentalDTO rentalDTO = facade.removeTenant(u1.getUserName(), r1.getId());
        System.out.println(rentalDTO);
        Assertions.assertEquals(0, rentalDTO.getUserDTOs().size());
    }


    //this test the createRental method
    @Test
    public void testCreateRental() {
        RentalDTO rentalDTO = new RentalDTO();
        rentalDTO.setStartDate("1 jan");
        rentalDTO.setEndDate("2 jan");
        rentalDTO.setPriceAnnual(50000);
        rentalDTO.setDeposit(10000);
        rentalDTO.setContactPerson("kenneth");
        HouseDTO houseDTO = new HouseDTO();
        houseDTO.setAddress("århus hovedgade");
        houseDTO.setCity("århus");
        houseDTO.setNumerOfRooms(7);
        UserDTO userDTO = new UserDTO();
        userDTO.setUserName("johndoe");
        userDTO.setUserPass("123");
        userDTO.setName("john");
        userDTO.setPhone("12345678");
        userDTO.setJob("skuespiller");


        RentalDTO rentalDTO1 = facade.createRental(houseDTO, userDTO, rentalDTO);
        Assertions.assertEquals(4, facade.getAllRentals().size());
    }

    //this test the editRental method
    @Test
    public void testEditRental() {
        RentalDTO rentalDTO = new RentalDTO();
        rentalDTO.setStartDate("1 jan");
        rentalDTO.setEndDate("2 jan");
        rentalDTO.setPriceAnnual(50000);
        rentalDTO.setDeposit(10000);
        rentalDTO.setContactPerson("kenneth");
        HouseDTO houseDTO = new HouseDTO();
        houseDTO.setAddress("tuborg hovedgade");
        houseDTO.setCity("århus");
        houseDTO.setNumerOfRooms(7);
        rentalDTO.setHouseDTO(houseDTO);

        RentalDTO rentalDTO1 = facade.updateAll(rentalDTO, r1.getId());
        Assertions.assertEquals("tuborg hovedgade", rentalDTO1.getHouseDTO().getAddress());

    }

}
