
import org.junit.*;
import java.util.*;
import play.*;
import play.jobs.*;
import play.test.*;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import play.mvc.Controller;

import models.*;
import controllers.*;

@Every("1min")
// @OnApplicationStart
public class Bootstrap_every5m extends Job {

    public void doJob() {
        System.out.println("5m");
        //Application.service_status();
        // Action(controllers.Application.service_status());
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        //get current date time with Date()
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        List<WorkUpdate> new_workUpdates = WorkUpdate.find("byStatus", "new").fetch();
        List<WorkUpdate> started_workUpdates = WorkUpdate.find("byStatus", "started").fetch();
        List<WorkUpdate> closed_workUpdates = WorkUpdate.find("byStatus", "closed").fetch();
        //renderTemplate("Application/service_status.html", new_workUpdates, started_workUpdates, closed_workUpdates);
        //render(new_workUpdates, started_workUpdates, closed_workUpdates);
        Application.service_monitor(new_workUpdates, started_workUpdates, closed_workUpdates);

        //System.out.println(new_workUpdates);
    }
}
