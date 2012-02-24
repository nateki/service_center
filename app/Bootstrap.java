
import org.junit.*;
import java.util.*;
import play.*;
import play.jobs.*;
import play.test.*;

import models.*;

@OnApplicationStart
public class Bootstrap extends Job {

    public void doJob() {
        // Check if the database is empty
        if (Customer.count() == 0) {
            System.out.println("start");
            Fixtures.loadModels("initial-data.yml");
        }
    }
}
