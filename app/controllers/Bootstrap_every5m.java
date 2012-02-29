package controllers;


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

@Every("5s")
// @OnApplicationStart
public class Bootstrap_every5m extends Job {

    public String vnew_workUpdates;

//    public Bootstrap_every5m(String vnew_workUpdates){
//        this.vnew_workUpdates=vnew_workUpdates;
//    }

    public void doJob() {
        System.out.println("5m");
        //Application.service_status();
        
        
        
         //Action(controllers.Application.service_status());
        Cache.set("new_workUpdates_p",Cache.get("new_workUpdates", List.class));
        Cache.set("started_workUpdates_p",Cache.get("started_workUpdates", List.class));
        Cache.set("closed_workUpdates_p",Cache.get("closed_workUpdates", List.class));
        Cache.set("date_p",Cache.get("date", Date.class));
        List<WorkUpdate> new_workUpdates = WorkUpdate.find("byStatus", "new").fetch();
        List<WorkUpdate> started_workUpdates = WorkUpdate.find("byStatus", "started").fetch();
        List<WorkUpdate> closed_workUpdates = WorkUpdate.find("byStatus", "closed").fetch();
      //  Cache.set(vnew_workUpdates,new_workUpdates);
          Cache.set("new_workUpdates",new_workUpdates);
        Cache.set("started_workUpdates",started_workUpdates);
        Cache.set("closed_workUpdates",closed_workUpdates);

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        //get current date time with Date()
        Date date = new Date();
        Cache.set("date",date);
        //Monitor m = new Monitor();
        //m.service_status();
//        //renderTemplate("Application/service_status.html", new_workUpdates, started_workUpdates, closed_workUpdates);
//        //render(new_workUpdates, started_workUpdates, closed_workUpdates);
//        Application.service_monitor(new_workUpdates, started_workUpdates, closed_workUpdates);
    //Application.service_status();
     //Action(Application.service_status);
        //System.out.println(new_workUpdates);
    }
}
