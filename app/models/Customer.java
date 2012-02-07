package models;

import java.util.*;
import javax.persistence.*;

import play.db.jpa.*;

@Entity
public class Customer extends Model {

    public String email;
    public String password;
    public String fullname;
    public boolean isAdmin;

    public Customer(String email, String password, String fullname) {
        this.email = email;
        this.password = password;
        this.fullname = fullname;
    }
    public static Customer connect(String email, String password) {
    return find("byEmailAndPassword", email, password).first();
}

}

