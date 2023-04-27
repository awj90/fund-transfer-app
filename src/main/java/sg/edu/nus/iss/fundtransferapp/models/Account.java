package sg.edu.nus.iss.fundtransferapp.models;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class Account {
    private String accountId;
    private String name;
    private Double balance;

    public String getAccountId() {
        return accountId;
    }
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Double getBalance() {
        return balance;
    }
    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public static Account create(SqlRowSet rs) {
        Account account = new Account();
        account.setAccountId(rs.getString("account_id"));
        account.setName(rs.getString("name"));
        account.setBalance(rs.getDouble("balance"));
        return account;
    }
}
