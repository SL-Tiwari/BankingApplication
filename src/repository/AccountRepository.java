package repository;
import domain.Account;
import domain.Customer;

import java.util.*;

public class AccountRepository {

    private final Map<String, Account> accountHashMap = new HashMap<>();

    public void saveAccount(Account account) {
        accountHashMap.put(account.getAccountNumber(),account);
    }

    public List<Account> findAll()
    {
        return new ArrayList<>(accountHashMap.values());
    }

    public Optional<Account> findByAccountNumber(String accountNumber)
    {
        return Optional.ofNullable(accountHashMap.get(accountNumber));
    }

    public List<Account> findByCustomerId(String customerId) {
        List<Account> result = new ArrayList<>();
        for (Account a : findAll())
        {
            if(a.getCustomerId().equals(customerId))
            {
                result.add(a);
            }
        }
        return result;
    }
}
