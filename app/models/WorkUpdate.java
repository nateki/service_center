package models;

import java.util.*;
import javax.persistence.*;

import play.db.jpa.*;

@Entity
public class WorkUpdate extends Model {

    public String ticket_no;
    public String status;
    public Date UpdatedAt;
    public Date Completion_Date;
    @ManyToOne
    public Customer customer;
    @ManyToOne
    public Item item;
    @ManyToOne
    public Service service;
    @ManyToOne
    public ServiceEngineer serviceEngineer;
    @Lob
    public String content;

    public WorkUpdate(Customer customer, Service service, Item item, String status) {
        this.item = item;
        this.customer = customer;
        this.service = service;
        this.status = status;
        this.UpdatedAt = new Date();
    }

    public String toString() {
        return ticket_no;
    }
}
