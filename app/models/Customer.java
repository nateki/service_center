package models;

import java.util.*;
import javax.persistence.*;

import play.db.jpa.*;

@Entity
public class Customer extends Model {

    public String email;
    public String password;
    public String fullname;
    public String status;
    @ManyToMany(cascade=CascadeType.PERSIST)
    public List<Item> items;
    @ManyToMany(cascade=CascadeType.PERSIST)
    public List<Service> services;
    public Customer(String email, String password, String fullname) {
        this.items = new ArrayList<Item>();
        this.services = new ArrayList<Service>();
        this.email = email;
        this.password = password;
        this.fullname = fullname;
        this.status = "new";
    }
    public Customer addItemService(Item i,Service s) {
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

