package service;
import domain.Account;
import domain.Transaction;

import java.util.List;

public interface BankService {

    String createAccount(String name, String email, String accountType);

    List<Account> listOfAccounts();

    void depositAmount(String accountNumber, double depositAmount, String note);

    void withdraw(String accountNumber, double withdrawAmount,String note);

    List<Account> searchAccountByCustomerName(String customerName);

    void transferFunds(String accountFrom, String accountTo, double amount, String transfer);

    List<Transaction> getStatementDetails(String accountNumber);
}
