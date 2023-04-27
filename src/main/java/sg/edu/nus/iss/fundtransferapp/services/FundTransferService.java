package sg.edu.nus.iss.fundtransferapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.nus.iss.fundtransferapp.exceptions.TransferNotSuccessfulException;
import sg.edu.nus.iss.fundtransferapp.models.Account;
import sg.edu.nus.iss.fundtransferapp.repository.AccountsRepository;

@Service
public class FundTransferService {
    
    @Autowired
    AccountsRepository appRepository;

    public List<Account> getAllAccounts() {
        return this.appRepository.getAllAccounts();
    }

    public boolean isExisting(String accountId) {
        Optional<Account> account = appRepository.getAccountById(accountId);
        if (account.isEmpty()) {
            return false;
        }
        return true;
    }

    public Optional<String> getAccountOwnerNameByAccountId(String accountId) {
        Optional<Account> account = appRepository.getAccountById(accountId);
        if (!account.isEmpty()) {
            return Optional.of(account.get().getName());
        }
        return Optional.empty();
    }

    public boolean isBalanceSufficient(String accountId, Double amount) {
        Optional<Account> account = appRepository.getAccountById(accountId);
        if (account.get().getBalance() >= amount) {
            return true;
        }
        return false;
    }

    @Transactional
    public void transferFunds(String fromAccountId, String toAccountId, Double amount) throws TransferNotSuccessfulException {
        Optional<Account> fromAccount = appRepository.getAccountById(fromAccountId);
        Optional<Account> toAccount = appRepository.getAccountById(toAccountId);

        if (fromAccount.isEmpty()) {
            throw new TransferNotSuccessfulException("Invalid source account id %s".formatted(fromAccountId));
        }
        if (toAccount.isEmpty()) {
            throw new TransferNotSuccessfulException("Invalid destination account id %s".formatted(toAccountId));
        }
        if (!isBalanceSufficient(fromAccountId, amount)) {
            throw new TransferNotSuccessfulException("Insufficient funds in source account id %s".formatted(fromAccountId));
        }
        if (!appRepository.debitFunds(fromAccountId, amount) || !appRepository.creditFunds(toAccountId, amount)) {
            throw new TransferNotSuccessfulException("Unsuccessful fund transfer");
        }

    }
}
