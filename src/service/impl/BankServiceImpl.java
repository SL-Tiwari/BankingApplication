package service.impl;
import domain.*;
import exception.*;
import repository.*;
import service.BankService;
import util.Validation;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class BankServiceImpl implements BankService{

    private static AccountRepository accountRepository = new AccountRepository();
    private static TransactionRepository transRepository = new TransactionRepository();
    private static CustomerRepository customerRepository = new CustomerRepository();

    Validation<String> validateName = (name)->
    {
        if(name == null || name.isBlank()) throw new ValidationException("Name is required");
    };

    Validation<String> validateEmail = (email)->
    {
        if(email == null || !email.contains("@")) throw new ValidationException("Enter a Valid email");
    };

    Validation<String> validateType = (type)->
    {
        if(type == null || !(type.equalsIgnoreCase("SAVINGS") || type.equalsIgnoreCase("CURRENT"))) throw new ValidationException("Enter a Valid Account Type");
    };

    Validation<Double> validateAmount = (amount)->{
      if(amount == null || (amount < 0)) throw new ValidationException("Amount must be greater than zero");
    };

    @Override
    public String createAccount(String name, String email, String accountType) {
        validateName.validate(name);
        validateEmail.validate(email);
        validateType.validate(accountType);

        String accountNumber=null;

        String customerid = UUID.randomUUID().toString();
        accountNumber = getAccountNumber(accountType);
        Account account = new Account(accountNumber,customerid,0,accountType);
        accountRepository.saveAccount(account);
        Customer customer = new Customer(customerid,name,email);
        customerRepository.addCustomer(customer);
        return accountNumber;
    }

    @Override
    public List<Account> listOfAccounts() {
       return accountRepository.findAll().stream().
               sorted(Comparator.comparing(Account::getAccountNumber)).
               collect(Collectors.toList());
    }

    @Override
    public void depositAmount(String accountNumber, double amount, String note) {
      validateAmount.validate(amount);
      Account account = accountRepository.findByAccountNumber(accountNumber).orElseThrow(()->
              new AccountNotFoundException("Account not found: " + accountNumber));
      account.setBalance(account.getBalance()+amount);
      String transactionId = UUID.randomUUID().toString();
      LocalDateTime timeStamp = LocalDateTime.now();
      Transaction transaction = new Transaction(transactionId,accountNumber,amount,timeStamp,note, Type.DEPOSIT);
      transRepository.add(transaction);
    }

    @Override
    public void withdraw(String accountNumber, double withdrawAmount, String note) {
        validateAmount.validate(withdrawAmount);
      Account account =  accountRepository.findByAccountNumber(accountNumber).orElseThrow(
                ()-> new AccountNotFoundException("Account not found: " + accountNumber));
    if(account.getBalance()<withdrawAmount) {
        throw new InsufficientFundsException("Insufficient funds");
    }
    account.setBalance(account.getBalance()-withdrawAmount);
    String transactionId = UUID.randomUUID().toString();
    LocalDateTime timeStamp = LocalDateTime.now();
    Transaction transaction = new Transaction(transactionId,accountNumber,withdrawAmount,timeStamp,note, Type.WITHDRAW);
    transRepository.add(transaction);
    }

    @Override
    public List<Account> searchAccountByCustomerName(String customerName) {
        validateName.validate(customerName);
        String custName = (customerName == null) ? "" : customerName.toLowerCase();
        List<Account> result = new ArrayList<>();
        for (Customer c : customerRepository.findAll())
        {
            if(c.getName().toLowerCase().contains(custName))
            {
                result.addAll(accountRepository.findByCustomerId(c.getId()));
            }
        }
        return result;
    }

    @Override
    public void transferFunds(String accountFrom, String accountTo, double amountToTransfer, String transfer) {
        validateAmount.validate(amountToTransfer);
        if(accountFrom.equals(accountTo)){
            throw new ValidationException("Cannot Transfer to the same account");
        }
        Account fromAccount = accountRepository.findByAccountNumber(accountFrom).orElseThrow(
                ()-> new AccountNotFoundException("Account not found: " + accountFrom)
        );
        Account toAccount = accountRepository.findByAccountNumber(accountTo).orElseThrow(
                ()-> new AccountNotFoundException("Account not found: " + accountFrom)
        );
        if(fromAccount.getBalance()<amountToTransfer)
        {
            throw new InsufficientFundsException("Insufficient funds to transfer");
        }
        fromAccount.setBalance(fromAccount.getBalance()-amountToTransfer);
        toAccount.setBalance(toAccount.getBalance()+amountToTransfer);
        LocalDateTime timeStamp = LocalDateTime.now();
        Transaction transferOutTx = new Transaction(UUID.randomUUID().toString(),accountFrom,amountToTransfer,timeStamp,"TRANSFERRED OUT", Type.TRANSFER_OUT);
        transRepository.add(transferOutTx);
        Transaction transferInTx = new Transaction(UUID.randomUUID().toString(),accountTo,amountToTransfer,timeStamp,"RECEIVED", Type.TRANSFER_IN);
        transRepository.add(transferInTx);
    }

    @Override
    public List<Transaction> getStatementDetails(String accountNumber) {
           Account account = accountRepository.findByAccountNumber(accountNumber).orElseThrow(
                   ()-> new AccountNotFoundException("Account not found: " + accountNumber)
           );
        return transRepository.findByAccountNumber(accountNumber)
                .stream().sorted(Comparator.comparing(Transaction::getTimeStamp))
                .collect(Collectors.toList());
    }

    private String getAccountNumber(String accountType) {
        validateType.validate(accountType);
        String accountNumber;
        int temp = accountRepository.findAll().size()+1;
        if(accountType.equals("SAVINGS"))
            accountNumber = String.format("SA%06d",temp);
        else
            accountNumber = String.format("CR%06d",temp);
        return accountNumber;
    }
}