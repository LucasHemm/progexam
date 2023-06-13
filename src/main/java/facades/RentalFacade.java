package facades;

import dtos.UserDTO;
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








}
