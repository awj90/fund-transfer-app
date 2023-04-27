package sg.edu.nus.iss.fundtransferapp.repository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.fundtransferapp.models.Account;

import static sg.edu.nus.iss.fundtransferapp.repository.DBQueries.*;

@Repository
public class AppRepository {
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Account> getAllAccounts() {
        List<Account> acc = new LinkedList<>();
        SqlRowSet rs = jdbcTemplate.queryForRowSet(SELECT_ALL_ACCOUNTS);
        while (rs.next()) {
            acc.add(Account.create(rs));
        }
        return acc;
    }

    public Optional<Account> getAccountById(String accountId) {
        SqlRowSet rs = jdbcTemplate.queryForRowSet(SELECT_ACCOUNT_BY_ID, accountId);
        if (rs.next()) {
            Account acc = Account.create(rs);
            return Optional.of(acc);
        }
        return Optional.empty();
    }
}
