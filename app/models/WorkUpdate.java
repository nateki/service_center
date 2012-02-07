package models;

import java.util.*;
import javax.persistence.*;

import play.db.jpa.*;

@Entity
public class WorkUpdate extends Model {

    public String author;
    public Date UpdatedAt;

    @Lob
    public String content;

    @ManyToOne
    public Item item;

    public WorkUpdate(Item item, String author, String content) {
        this.item = item;
        this.author = author;
        this.content = content;
        this.UpdatedAt = new Date();
    }

}