package by.petropavlovskaja.springpharmacy;

import by.petropavlovskaja.springpharmacy.dao.AccountDaoImpl;
import by.petropavlovskaja.springpharmacy.dao.AccountRoleDao;
import by.petropavlovskaja.springpharmacy.dao.IAccountDao;
import by.petropavlovskaja.springpharmacy.entity.Account;
import by.petropavlovskaja.springpharmacy.entity.AccountRole;
import by.petropavlovskaja.springpharmacy.entity.Login;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        IAccountDao accountDao = new AccountDaoImpl();
/*        Account account = AccountDao.getAccount("Ivan", "123");
        if (account != null) {
            System.out.println(account);
        } else {
            System.out.println("Account wasn't found");
        }*/

        System.out.println(accountDao.getAllAccounts());
        System.out.println("Trying to create Account");

        testCreateAccount(accountDao);
        System.out.println(accountDao.getAllAccounts());



    }

    private static void testCreateAccount(IAccountDao accountDao){
        List<AccountRole> roleList = AccountRoleDao.getAllAccountRole();
        Account account = new Account();
        account.setSurname("Pavlovich2");
        account.setName("Pavel1");
        account.setPhone("123456");
        account.setRole(getRole(roleList, "customer"));

        Login login = new Login();
        login.setLogin("Pavlusha2");
        login.setPassword("123456987");
        login.setSalt("123qwerty");

        accountDao.saveNewAccount(account, login);

    }

    private static AccountRole getRole(List<AccountRole> roleList, String roleName){
        return roleList.stream()
                .filter(o->o.getName().equalsIgnoreCase(roleName))
                .findFirst()
                .orElse(null);
    }
}
