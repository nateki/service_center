package models;

import java.util.*;
import javax.persistence.*;

import play.db.jpa.*;

@Entity
public class Item extends Model {

    public String item_name;
    public Item(String item_name) {
        this.item_name = item_name;
            }
    public String toString() {
    return item_name;
}
    }