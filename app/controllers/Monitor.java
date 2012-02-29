/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

/**
 *
 * @author Kavinaesh
 */

import play.*;
import play.mvc.*;

import java.util.*;
import java.util.ArrayList;
import java.util.List;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


import models.*;
import controllers.*;
import play.jobs.*;
import play.jobs.Job;
import jobs.*;
import play.cache.Cache;


public class Monitor extends Controller{


    public static void service_status() {
        //Job b = new Bootstrap_every5m("new_workUpdates");

        //System.out.println(dateFormat.format(date));

        //List<WorkUpdate> new_workUpdates = WorkUpdate.find("byStatus", "new").fetch();
        //List<WorkUpdate> started_workUpdates = WorkUpdate.find("byStatus", "started").fetch();
        //List<WorkUpdate> closed_workUpdates = WorkUpdate.find("byStatus", "closed").fetch();
        //render(new_workUpdates, started_workUpdates, closed_workUpdates);
        Date date = Cache.get("date", Date.class);
        Date date_p = Cache.get("date_p", Date.class);
        List<WorkUpdate> new_workUpdates_p = Cache.get("new_workUpdates_p", List.class);
        List<WorkUpdate> started_workUpdates_p = Cache.get("started_workUpdates_p", List.class);
        List<WorkUpdate> closed_workUpdates_p = Cache.get("closed_workUpdates_p", List.class);
        //List<WorkUpdate> new_workUpdates = Cache.get(b.vnew_workUpdates, List.class);
        List<WorkUpdate> new_workUpdates = Cache.get("new_workUpdates", List.class);
        List<WorkUpdate> started_workUpdates = Cache.get("started_workUpdates", List.class);
        List<WorkUpdate> closed_workUpdates = Cache.get("closed_workUpdates", List.class);
        render("Application/service_status.html",new_workUpdates, started_workUpdates, closed_workUpdates,date, date_p, new_workUpdates_p, started_workUpdates_p, closed_workUpdates_p);
    }

}
