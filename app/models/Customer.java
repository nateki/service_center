package models;

import java.util.*;
import javax.persistence.*;

import play.db.jpa.*;
import play.data.validation.*;

@Entity
public class Customer extends Model {

    @Email
    @Required
    public String email;
    @Required
    public String password;
    @Required
    public String fullname;
    public String status;
    public boolean isAdmin;
    @ManyToMany(cascade = CascadeType.PERSIST)
    public List<Item> items;
    @ManyToMany(cascade = CascadeType.PERSIST)
    public List<Service> services;

    public Customer(String email, String password, String fullname) {
        this.items = new ArrayList<Item>();
        this.services = new ArrayList<Service>();
        this.email = email;
        this.password = password;
        this.fullname = fullname;
        this.status = "new";
    }

    public Customer addItemService(Item i, Service s) {
        this.items.add(i);
        this.services.add(s);
        this.save();
        return this;
    }

    public static Customer connect(String email, String password) {
        return find("byEmailAndPassword", email, password).first();
    }

    public String toString() {
        return fullname;
    }
}
