package sg.edu.nus.iss.fundtransferapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.fundtransferapp.models.Account;
import sg.edu.nus.iss.fundtransferapp.repository.AppRepository;

@Service
public class AppService {
    
    @Autowired
    AppRepository appRepository;

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

    public boolean isBalanceSufficient(String accountId, Double amount) {
        Optional<Account> account = appRepository.getAccountById(accountId);
        if (account.get().getBalance() >= amount) {
            return true;
        }
        return false;
    }
}
