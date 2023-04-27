package sg.edu.nus.iss.fundtransferapp.models;

import java.util.Date;

import jakarta.json.Json;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public class Transfer {

    @Size(min=10, max=10, message="Please enter a 10-digit account ID.")
     private String fromAccountId;

     @Size(min=10, max=10, message="Please enter a 10-digit account ID.")
     private String toAccountId;

     @Min(value=10, message="Minimum transfer amount is 10 dollars.")
     private Double amount;  
     
     private String comments;

     private String id;

     private long date;

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
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public long getDate() {
        return date;
    }
    public void setDate(long date) {
        this.date = date;
    }

    public String toJsonString() {
        return Json.createObjectBuilder()
                    .add("transactionId", this.getId())
                    .add("date", new Date(this.getDate()).toString())
                    .add("from_account", this.getFromAccountId())
                    .add("to_account", this.getToAccountId())
                    .add("amount", this.getAmount())
                    .build()
                    .toString();

    }
}
