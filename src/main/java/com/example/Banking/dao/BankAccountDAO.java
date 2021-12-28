package com.example.Banking.dao;

import com.example.Banking.entities.BankAccount;
import com.example.Banking.exception.BankTransactionException;
import com.example.Banking.model.BankAccountInfo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class BankAccountDAO {

    @Autowired
    private EntityManager entityManager;

    public BankAccountDAO() {
    }

    public BankAccount findById(int id) {
        return this.entityManager.find(BankAccount.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<BankAccountInfo> listBankAccountInfo() {
        String sql = "Select new " + BankAccountInfo.class.getName() + "(e.id, e.fullName, e.balance) " + " from " + BankAccount.class.getName() + " e ";
        Query query = entityManager.createQuery(sql, BankAccountInfo.class);
        return query.getResultList();
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void addAmount(int id, double amount) throws BankTransactionException {
        BankAccount account = this.findById(id);
        if (account == null) {
            throw new BankTransactionException("Account not found " + id);
        }
        double newBalance = account.getBalance() + amount;
        if (account.getBalance() + amount < 0) {
            throw new BankTransactionException("The money in account " + id + " is not enough (" + account.getBalance() + ")");
        }
        account.setBalance(newBalance);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = BankTransactionException.class)
    public void sendMoney(int fromAccountId, int toAccountId, double amount) throws BankTransactionException {
        addAmount(toAccountId, amount);
        addAmount(fromAccountId, -amount);
    }
}
