package models;

import java.util.*;
import javax.persistence.*;

import play.db.jpa.*;

@Entity
public class Item extends Model {

    public String item_name;
    public Date entry_date;

    @Lob
    public String item_description;

    @ManyToOne
    public Customer owner;
    @OneToMany(mappedBy="item", cascade=CascadeType.ALL)
public List<WorkUpdate> WorkUpdates;
    public Item(Customer owner, String item_name, String item_description) {
        this.WorkUpdates = new ArrayList<WorkUpdate>();
        this.owner = owner;
        this.item_name = item_name;
        this.item_description = item_description;
        this.entry_date = new Date();
    }


public Item addWorkUpdate(String author, String content) {
    WorkUpdate newWorkUpdate = new WorkUpdate(this, author, content).save();
    this.WorkUpdates.add(newWorkUpdate);
    this.save();
    return this;
}

}