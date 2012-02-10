package models;

import java.util.*;
import javax.persistence.*;

import play.db.jpa.*;

@Entity
public class Service extends Model {

    public String service_name;
        @Lob
    public String service_description;

    public Service(String service_name, String service_description) {
        this.service_name = service_name;
        this.service_description = service_description;
    }
public String toString() {
    return service_name;
}
}