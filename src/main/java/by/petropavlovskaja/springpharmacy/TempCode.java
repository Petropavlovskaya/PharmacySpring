package by.petropavlovskaja.springpharmacy;

import by.petropavlovskaja.springpharmacy.entity.Account;
import by.petropavlovskaja.springpharmacy.entity.Login;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class TempCode {
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("OnlinePharmacy");

    // AccountDao
    public static void saveNewAccount(Account account, Login login) {

        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        final EntityManager entityManager1 = entityManagerFactory.createEntityManager();

        EntityTransaction accountTransaction = null;
        EntityTransaction loginTransaction = null;

        try {
            accountTransaction = entityManager.getTransaction();
            accountTransaction.begin();
            entityManager.persist(account);
            accountTransaction.commit();

            login.setAccount(account);
            loginTransaction = entityManager1.getTransaction();
            loginTransaction.begin();
            entityManager1.persist(login);
            loginTransaction.commit();

            System.out.println("Account transaction = "+accountTransaction.toString() + ". Login transaction = "+loginTransaction.toString());

        } catch (Exception e) {
            if (accountTransaction != null && loginTransaction != null) {
                accountTransaction.rollback();
                loginTransaction.rollback();
                System.out.println(e);
            }
        } finally {
            entityManager.close();
        }
    }

}
