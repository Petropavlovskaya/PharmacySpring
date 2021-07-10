package by.petropavlovskaja.springpharmacy.dao;

import by.petropavlovskaja.springpharmacy.entity.Account;
import by.petropavlovskaja.springpharmacy.entity.Login;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Component
public class AccountDaoImpl implements IAccountDao{

//    @PersistenceUnit(name = "OnlinePharmacy")
//    private static EntityManagerFactory entityManagerFactory;
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("OnlinePharmacy");

//    @PersistenceContext(name = "OnlinePharmacy")
//    private static EntityManager manager;

    @Override
    public Account getAccount(String login, String password) {
        Account account = new Account();
        if (login == null || password == null) {
            return account;
        }

        final EntityManager manager = entityManagerFactory.createEntityManager();
        String sqlQuery = "SELECT a FROM Account a WHERE a.name = :login ";
        TypedQuery<Account> query = manager.createQuery(sqlQuery, Account.class);
        query.setParameter("login", login);
        try {
            account = query.getResultStream().findFirst().orElse(new Account());
        } finally {
            manager.close();
        }
        return account;
    }

    @Override
    public List<Account> getAllAccounts() {

        final EntityManager manager = entityManagerFactory.createEntityManager();
        String sqlQuery = "SELECT a FROM Account a";
        TypedQuery<Account> query = manager.createQuery(sqlQuery, Account.class);
        List<Account> accountList = new ArrayList<>();
        try {
            accountList.addAll(query.getResultList());
        } finally {
            manager.close();
        }
        return accountList;
    }

    @Override
    public void saveNewAccount(Account account, Login login) {

        final EntityManager manager = entityManagerFactory.createEntityManager();

        EntityTransaction transaction = null;

        try {
            transaction = manager.getTransaction();
            transaction.begin();
            manager.persist(account);
            login.setAccount(account);
            manager.persist(login);
            transaction.commit();

            System.out.println("Account transaction = "+transaction);

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                System.out.println(e);
            }
        } finally {
            manager.close();
        }
    }


    private static void isLoginPresentInDB() {
    }
}
