package sg.edu.nus.iss.fundtransferapp.repository;

public class DBQueries {
    public static final String SELECT_ALL_ACCOUNTS="select account_id, name, balance from accounts";
    public static final String SELECT_ACCOUNT_BY_ID="select account_id, name, balance from accounts where account_id = ?";
    public static final String CREDIT_ACCOUNT_BALANCE="update accounts set balance = balance + ? where account_id = ?";
    public static final String DEBIT_ACCOUNT_BALANCE="update accounts set balance = balance - ? where account_id = ?";
}
