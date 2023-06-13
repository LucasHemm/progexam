package facades;

import dtos.RentalDTO;
import dtos.UserDTO;
import entities.Rental;
import entities.Role;
import entities.User;
import security.errorhandling.AuthenticationException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;
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
     *
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


    public List<RentalDTO> getAllRentals(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Rental> query = em.createQuery("SELECT r FROM Rental r", Rental.class);
        List<Rental> rentals = query.getResultList();
        return RentalDTO.getDtos(rentals);
    }





}
