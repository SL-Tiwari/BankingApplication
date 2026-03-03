package repository;

import domain.Transaction;

import java.util.*;

public class TransactionRepository {

    //String - account Number
    private final Map<String, List<Transaction>> transactionByAccount = new HashMap<>();

    public String add(Transaction transaction) {
        List<Transaction> list = transactionByAccount.computeIfAbsent(transaction.getAccountNumber(),k->new ArrayList<>());
        list.add(transaction);
        return "Transaction has been added to the account";
    }


    public List<Transaction> findByAccountNumber(String accountNumber) {
       return new ArrayList<>(transactionByAccount.getOrDefault(accountNumber,Collections.emptyList()));
    }
}
