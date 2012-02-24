
import org.junit.*;
import java.util.*;
import play.*;
import play.jobs.*;
import play.test.*;
import play.cache.Cache;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import play.mvc.Controller;

import models.*;
import controllers.*;

@Every("10s")
// @OnApplicationStart
public class Bootstrap_every5m extends Job {

    public void doJob() {
        System.out.println("5m");
        //Application.service_status();
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        //get current date time with Date()
        Date date = new Date();
        System.out.println(dateFormat.format(date));
         //Action(controllers.Application.service_status());
        List<WorkUpdate> new_workUpdates = WorkUpdate.find("byStatus", "new").fetch();
        List<WorkUpdate> started_workUpdates = WorkUpdate.find("byStatus", "started").fetch();
        List<WorkUpdate> closed_workUpdates = WorkUpdate.find("byStatus", "closed").fetch();
        Cache.set("new_workUpdates",new_workUpdates,"10mn");
        Cache.set("started_workUpdates",started_workUpdates,"10mn");
        Cache.set("closed_workUpdates",closed_workUpdates,"10mn");
        List<WorkUpdate> new_workUpdates_c = Cache.get("new_workUpdates",List.class);
        System.out.println(new_workUpdates_c);
        List<WorkUpdate> started_workUpdates_c = Cache.get("started_workUpdates",List.class);
        System.out.println(started_workUpdates_c);
        List<WorkUpdate> closed_workUpdates_c = Cache.get("closed_workUpdates",List.class);
        System.out.println(closed_workUpdates_c);
//        //renderTemplate("Application/service_status.html", new_workUpdates, started_workUpdates, closed_workUpdates);
//        //render(new_workUpdates, started_workUpdates, closed_workUpdates);
//        Application.service_monitor(new_workUpdates, started_workUpdates, closed_workUpdates);
    //Application.service_status();
     //Action(Application.service_status);
        //System.out.println(new_workUpdates);
    }
}
