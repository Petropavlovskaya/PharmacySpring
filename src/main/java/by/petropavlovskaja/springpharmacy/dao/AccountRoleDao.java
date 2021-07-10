package by.petropavlovskaja.springpharmacy.dao;

import by.petropavlovskaja.springpharmacy.entity.AccountRole;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Component
public class AccountRoleDao {
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("OnlinePharmacy");

    public static List<AccountRole> getAllAccountRole(){
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        String sqlQuery = "SELECT r FROM AccountRole r";
        TypedQuery<AccountRole> query = entityManager.createQuery(sqlQuery, AccountRole.class);
        List<AccountRole> roleList = new ArrayList<>();
        try {
            roleList.addAll(query.getResultList());
        }finally {
            entityManager.close();
        }
        return roleList;
    }

}
