package sg.edu.nus.iss.fundtransferapp.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public class Transfer {

    @Size(min=10, max=10, message="Please enter a 10-digit account ID.")
     private String fromAccountId;

     @Size(min=10, max=10, message="Please enter a 10-digit account ID.")
     private String toAccountId;

     @Min(value=1, message="Transfer amount must be more than 0.")
     private Double amount;  
     
     private String comments;

    public String getFromAccountId() {
        return fromAccountId;
    }
    public void setFromAccountId(String fromAccountId) {
        this.fromAccountId = fromAccountId;
    }
    public String getToAccountId() {
        return toAccountId;
    }
    public void setToAccountId(String toAccountId) {
        this.toAccountId = toAccountId;
    }
    public Double getAmount() {
        return amount;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }
    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }

}
