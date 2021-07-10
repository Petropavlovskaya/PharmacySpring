package by.petropavlovskaja.springpharmacy.dao;

import by.petropavlovskaja.springpharmacy.entity.Account;
import by.petropavlovskaja.springpharmacy.entity.Login;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IAccountDao {
    Account getAccount(String login, String password);
    List<Account> getAllAccounts();
    void saveNewAccount(Account account, Login login);
}
